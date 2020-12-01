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

import java.util.*;
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
        return null;
    }

    @Override
    public void deleteMatchStat(MatchStat matchStat) {

    }
}
