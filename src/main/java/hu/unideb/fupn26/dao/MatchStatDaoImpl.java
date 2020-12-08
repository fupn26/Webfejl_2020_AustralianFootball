package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.*;
import hu.unideb.fupn26.dao.repository.MatchRepository;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.PlayerRepository;
import hu.unideb.fupn26.dao.repository.TeamRepository;
import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.MatchStat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
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
    public void createMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException, MatchStatAlreadyExists {
        MatchEntity matchEntity = queryMatchById(matchStat.getMatchId());
        PlayerEntity playerEntity = queryPlayerById(matchStat.getPlayerId());
        TeamEntity teamEntity = queryTeamById(matchStat.getTeamId());

        if (matchStatRepository.findById(new MatchStatId(matchEntity, playerEntity)).isEmpty())
            throw new MatchStatAlreadyExists(String.format("Match stat already exists: %s", matchStat));

        MatchStatEntity matchStatEntity = MatchStatEntity.builder()
                .id(new MatchStatId(matchEntity, playerEntity))
                .team(teamEntity)
                .location(matchEntity.getTeam1().getId() == matchStat.getTeamId() ?
                        matchEntity.getTeam1Location() :
                        matchEntity.getTeam2Location()
                )
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

        log.info("MatchStatEntity: {}", matchStatEntity);
        try {
            matchStatRepository.save(matchStatEntity);
        } catch (Exception e) {
            log.error("Can't save new match stat: {}", e.getMessage());
        }
    }

    @Override
    public Collection<MatchStat> readAll() {
        return StreamSupport.stream(matchStatRepository.findAll().spliterator(), false)
                .map(entity -> new MatchStat(
                        entity.getId().getMatch().getId(),
                        entity.getId().getPlayer().getId(),
                        entity.getTeam().getId(),
                        entity.getKicks(),
                        entity.getMarks(),
                        entity.getHandballs(),
                        entity.getDisposals(),
                        entity.getGoals(),
                        entity.getBehinds(),
                        entity.getHitOuts(),
                        entity.getTackles(),
                        entity.getRebound50s(),
                        entity.getInside50s(),
                        entity.getClearances(),
                        entity.getClangers(),
                        entity.getFreeKicksFor(),
                        entity.getFreeKicksAgainst(),
                        entity.getBrownlowVotes(),
                        entity.getContestedPossessions(),
                        entity.getUncontestedPossessions(),
                        entity.getContestedMarks(),
                        entity.getMarksInside50(),
                        entity.getOnePercenters(),
                        entity.getBounces(),
                        entity.getGoalAssist(),
                        entity.getPercentageOfGamePlayed()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public void updateMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException, UnknownMatchStatException {
        MatchEntity matchEntity = queryMatchById(matchStat.getMatchId());
        PlayerEntity playerEntity = queryPlayerById(matchStat.getPlayerId());
        TeamEntity teamEntity = queryTeamById(matchStat.getTeamId());

        MatchStatEntity matchStatEntity = queryMatchStatById(new MatchStatId(matchEntity, playerEntity));

        matchStatEntity.setTeam(teamEntity);
        matchStatEntity.setLocation(matchEntity.getTeam1().getId() == matchStat.getTeamId() ?
                        matchEntity.getTeam1Location() :
                        matchEntity.getTeam2Location()
                );
        matchStatEntity.setKicks(matchStat.getKicks());
        matchStatEntity.setMarks(matchStat.getMarks());
        matchStatEntity.setHandballs(matchStat.getHandballs());
        matchStatEntity.setDisposals(matchStat.getDisposals());
        matchStatEntity.setGoals(matchStat.getGoals());
        matchStatEntity.setBehinds(matchStat.getBehinds());
        matchStatEntity.setHitOuts(matchStat.getHitOuts());
        matchStatEntity.setTackles(matchStat.getTackles());
        matchStatEntity.setRebound50s(matchStat.getRebound50s());
        matchStatEntity.setInside50s(matchStat.getInside50s());
        matchStatEntity.setClearances(matchStat.getClearances());
        matchStatEntity.setClangers(matchStat.getClangers());
        matchStatEntity.setFreeKicksFor(matchStat.getFreeKicksFor());
        matchStatEntity.setFreeKicksAgainst(matchStat.getFreeKicksAgainst());
        matchStatEntity.setBrownlowVotes(matchStat.getBrownlowVotes());
        matchStatEntity.setContestedPossessions(matchStat.getContestedPossessions());
        matchStatEntity.setUncontestedPossessions(matchStat.getUncontestedPossessions());
        matchStatEntity.setContestedMarks(matchStat.getContestedMarks());
        matchStatEntity.setMarksInside50(matchStat.getMarksInside50());
        matchStatEntity.setOnePercenters(matchStat.getOnePercenters());
        matchStatEntity.setBounces(matchStat.getBounces());
        matchStatEntity.setGoalAssist(matchStat.getGoalAssist());
        matchStatEntity.setPercentageOfGamePlayed(matchStat.getPercentageOfGamePlayed());

        log.info("MatchStatEntity: {}", matchStatEntity);
        try {
            matchStatRepository.save(matchStatEntity);
        } catch (Exception e) {
            log.error("Can't update match stat: {}", matchStat);
        }
    }

    @Override
    public void deleteMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException {
        MatchEntity matchEntity = queryMatchById(matchStat.getMatchId());
        PlayerEntity playerEntity = queryPlayerById(matchStat.getPlayerId());

        try {
            matchStatRepository.deleteById(new MatchStatId(matchEntity, playerEntity));
        } catch (Exception e) {
            log.error("Can't delete match stat: {}", matchStat);
        }
    }

    private MatchEntity queryMatchById(String id) throws UnknownMatchException {
        Optional<MatchEntity> matchEntity = matchRepository.findById(id);
        if (matchEntity.isEmpty())
            throw new UnknownMatchException(String.format("Match not found with the given ID: %s", id));

        return matchEntity.get();
    }

    private PlayerEntity queryPlayerById(Integer id) throws UnknownPlayerException {
        Optional<PlayerEntity> playerEntity = playerRepository.findById(id);
        if (playerEntity.isEmpty())
            throw new UnknownPlayerException(String.format("Player not found with the given ID: %d", id));

        return playerEntity.get();
    }

    private MatchStatEntity queryMatchStatById(MatchStatId id) throws UnknownMatchStatException {
        Optional<MatchStatEntity> matchStatEntity = matchStatRepository.findById(id);
        if (matchStatEntity.isEmpty())
            throw new UnknownMatchStatException(String.format("Match stat not found with the given ID: %d", id));

        return matchStatEntity.get();
    }

    private TeamEntity queryTeamById(Integer id) throws UnknownTeamException {
        Optional<TeamEntity> teamEntity = teamRepository.findById(id);
        if (teamEntity.isEmpty())
            throw new UnknownTeamException(String.format("Team not found with the given ID: %d", id));

        return teamEntity.get();
    }
}
