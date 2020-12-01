package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.*;
import hu.unideb.fupn26.dao.repository.MatchRepository;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.PlayerRepository;
import hu.unideb.fupn26.dao.repository.TeamRepository;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.MatchStat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.GeneratedValue;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchStatDaoImpl implements MatchStatDao{

    private final MatchStatRepository matchStatRepository;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;
    private final PlayerRepository playerRepository;

    @Override
    public void createMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException {
        MatchStatEntity matchStatEntity;
        MatchEntity matchEntity = queryMatchByMatchStat(matchStat);
        PlayerEntity playerEntity = queryPlayerByName(matchStat.getPlayerFirstName()
                ,matchStat.getPlayerLastName());
        TeamEntity playerTeamEntity = queryTeamByName(matchStat.getPlayerTeam());
        MatchStatId matchStatId = new MatchStatId(matchEntity, playerEntity);

        matchStatEntity = MatchStatEntity.builder()
                .id(matchStatId)
                .team(playerTeamEntity)
                .location(matchStat.getLocation())
                .kicks(matchStat.getKicks())
                .marks(matchStat.getMarks())
                .handballs(matchStat.getHandballs())
                .disposals(matchStat.getDisposals())
                .goals(matchStat.getGoals())
                .behinds(matchStat.getBehinds())
                .hitOuts(matchStat.getHitOuts())
                .tackles(matchStat.getTackles())
                .rebound50s(matchStat.getRebound50s())
                .inside50s(matchStat.getInside50s())
                .clearances(matchStat.getClearances())
                .clangers(matchStat.getClangers())
                .freeKicksFor(matchStat.getFreeKicksFor())
                .freeKicksAgainst(matchStat.getFreeKicksAgainst())
                .brownlowVotes(matchStat.getBrownlowVotes())
                .contestedPossessions(matchStat.getContestedPossessions())
                .uncontestedPossessions(matchStat.getUncontestedPossessions())
                .contestedMarks(matchStat.getContestedMarks())
                .marksInside50(matchStat.getMarksInside50())
                .onePercenters(matchStat.getOnePercenters())
                .bounces(matchStat.getBounces())
                .goalAssist(matchStat.getGoalAssist())
                .percentageOfGamePlayed(matchStat.getPercentageOfGamePlayed())
                .build();

        try {
            matchStatRepository.save(matchStatEntity);
        } catch (Exception e) {
            log.error("Can't save new match stat: {}", e.getMessage());
            e.printStackTrace();
        }
    }

    private PlayerEntity queryPlayerByName(String firstName, String lastname) throws UnknownPlayerException {
        Optional<PlayerEntity> playerEntity = playerRepository.findByFirstNameAndLastName(firstName, lastname).stream()
                .findFirst();

        if (playerEntity.isEmpty()) {
            log.error("Player not found: {} {}", firstName, lastname);
            throw new UnknownPlayerException(String.format("Player not found: %s %s", firstName, lastname));
        }

        log.trace("Player Entity: {}", playerEntity);
        return playerEntity.get();
    }

    private MatchEntity queryMatchByMatchStat(MatchStat matchStat) throws UnknownMatchException {
        int playerTeamId;
        int opponentTeamId;
        String playerTeamVsOpponentTeamMatch;
        String opponentTeamVsPlayerTeamMatch;

        try {
            playerTeamId = queryTeamByName(matchStat.getPlayerTeam()).getId();
            opponentTeamId = queryTeamByName(matchStat.getOpponentTeam()).getId();
        } catch (UnknownTeamException e) {
            log.error("Team not found!");
            throw new UnknownMatchException(String.format("Match not found: %s", e.getMessage()));
        }

        playerTeamVsOpponentTeamMatch = String.format("%d_%s_%d_%d"
                ,matchStat.getSeason()
                ,matchStat.getRound()
                ,playerTeamId
                ,opponentTeamId
        );

        opponentTeamVsPlayerTeamMatch = String.format("%d_%s_%d_%d"
                ,matchStat.getSeason()
                ,matchStat.getRound()
                ,opponentTeamId
                ,playerTeamId
        );

        Optional<MatchEntity> matchEntity = StreamSupport.stream (matchRepository
                .findAllById(Arrays.asList(playerTeamVsOpponentTeamMatch, opponentTeamVsPlayerTeamMatch))
                .spliterator(), false)
                .filter(entity -> {
                    if (entity.getTeam1().getId() == playerTeamId
                            && entity.getTeam1Location().equals(matchStat.getLocation()))
                        return true;
                    else if (entity.getTeam2().getId() == playerTeamId
                            && entity.getTeam2Location().equals(matchStat.getLocation()))
                        return true;
                    else
                        return false;
                })
                .findFirst();

        if (matchEntity.isEmpty()) {
            log.error("Match not found");
            throw new UnknownMatchException("Match not found");
        }

        log.trace("Match Entity: {}", matchEntity);
        return matchEntity.get();
    }

    private TeamEntity queryTeamByName(String team) throws UnknownTeamException {
        Optional<TeamEntity> teamEntity = teamRepository.findByName(team);

        if (teamEntity.isEmpty()) {
            throw new UnknownTeamException(String.format("Team not found: %s", team));
        }

        log.trace("Team Entity: {}", teamEntity);
        return teamEntity.get();
    }

    @Override
    public Collection<MatchStat> readAll() {
        return StreamSupport.stream(matchStatRepository.findAll().spliterator(), false)
                .map(entity -> MatchStat.builder()
                        .playerFirstName(entity.getId().getPlayer().getFirstName())
                        .playerLastName(entity.getId().getPlayer().getLastName())
                        .playerTeam(entity.getTeam().getName())
                        .opponentTeam(queryOpponentTeamName(entity))
                        .season(entity.getId().getMatch().getSeason())
                        .round(entity.getId().getMatch().getRound())
                        .location(entity.getLocation())
                        .kicks(entity.getKicks())
                        .marks(entity.getMarks())
                        .handballs(entity.getHandballs())
                        .disposals(entity.getDisposals())
                        .goals(entity.getGoals())
                        .behinds(entity.getBehinds())
                        .hitOuts(entity.getHitOuts())
                        .tackles(entity.getTackles())
                        .rebound50s(entity.getRebound50s())
                        .inside50s(entity.getInside50s())
                        .clearances(entity.getClearances())
                        .clangers(entity.getClangers())
                        .freeKicksFor(entity.getFreeKicksFor())
                        .freeKicksAgainst(entity.getFreeKicksAgainst())
                        .brownlowVotes(entity.getBrownlowVotes())
                        .contestedPossessions(entity.getContestedPossessions())
                        .uncontestedPossessions(entity.getUncontestedPossessions())
                        .contestedMarks(entity.getContestedMarks())
                        .marksInside50(entity.getMarksInside50())
                        .onePercenters(entity.getOnePercenters())
                        .bounces(entity.getBounces())
                        .goalAssist(entity.getGoalAssist())
                        .percentageOfGamePlayed(entity.getPercentageOfGamePlayed())
                        .build()
                )
                .collect(Collectors.toList());
    }

    private String queryOpponentTeamName(MatchStatEntity entity) {
        String playerTeamName = entity.getTeam().getName();
        String team1Name = entity.getId().getMatch().getTeam1().getName();
        String team2Name = entity.getId().getMatch().getTeam2().getName();

        return playerTeamName.equals(team1Name) ? team2Name : team1Name;
    }

    @Override
    public void deleteMatchStat(MatchStat matchStat) {

    }
}
