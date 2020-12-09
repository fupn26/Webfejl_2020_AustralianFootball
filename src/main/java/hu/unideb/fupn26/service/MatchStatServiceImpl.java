package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.MatchStatDao;
import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.MatchStat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchStatServiceImpl implements MatchStatService{

    private final MatchStatDao matchStatDao;

    @Override
    public Collection<MatchStat> getAllMatchStat() {
        return matchStatDao.readAll();
    }

    @Override
    public Collection<MatchStat> getAllMatchStatByPlayer(Integer playerId) {
        return matchStatDao.readAll().stream()
                .filter(matchStat -> matchStat.getPlayerId().equals(playerId))
                .collect(Collectors.toList());
    }

    @Override
    public Collection<MatchStat> getAllMatchStatByTeam(Integer teamId) {
        return matchStatDao.readAll().stream()
                .filter(matchStat -> matchStat.getTeamId().equals(teamId))
                .collect(Collectors.toList());
    }

    @Override
    public void recordMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException, InvalidMatchStatArgumentException, MatchStatAlreadyExistsException {
        validateMatchStat(matchStat);

        matchStatDao.createMatchStat(matchStat);
    }

    @Override
    public void updateMatchStat(MatchStat matchStat) throws InvalidMatchStatArgumentException, UnknownPlayerException, UnknownTeamException, UnknownMatchException, UnknownMatchStatException {
        validateMatchStat(matchStat);

        matchStatDao.updateMatchStat(matchStat);
    }

    @Override
    public void deleteMatchStat(MatchStat matchStat) throws UnknownPlayerException, UnknownMatchException, InvalidMatchStatArgumentException {
        if (matchStat.getMatchId() == null)
            throw new InvalidMatchStatArgumentException("Match ID is null");
        if (matchStat.getPlayerId() == null)
            throw new InvalidMatchStatArgumentException("Player ID is null");

        matchStatDao.deleteMatchStat(matchStat);
    }

    protected void validateMatchStat(MatchStat matchStat) throws InvalidMatchStatArgumentException {
        if (matchStat.getMatchId() == null)
            throw new InvalidMatchStatArgumentException("Match ID is null");
        if (matchStat.getPlayerId() == null)
            throw new InvalidMatchStatArgumentException("Player ID is null");
        if (matchStat.getTeamId() == null || matchStat.getGoals() == null || matchStat.getBehinds() == null)
            throw new InvalidMatchStatArgumentException("Field's value is null. " +
                    "TeamId, goals and behinds can't be null!");

        if (!Arrays.asList(matchStat.getMatchId().split("_")).contains(matchStat.getTeamId().toString()))
            throw new InvalidMatchStatArgumentException("The given team isn't participant of the given match");

        if ((matchStat.getKicks() != null && matchStat.getKicks() < 0) ||
                (matchStat.getMarks() != null && matchStat.getMarks() < 0) ||
                (matchStat.getHandballs() != null && matchStat.getHandballs() < 0) ||
                (matchStat.getDisposals() != null && matchStat.getDisposals() < 0) ||
                (matchStat.getGoals() != null && matchStat.getGoals() < 0) ||
                (matchStat.getBehinds() != null && matchStat.getBehinds() < 0) ||
                (matchStat.getHitOuts() != null && matchStat.getHitOuts() < 0) ||
                (matchStat.getTackles() != null && matchStat.getTackles() < 0) ||
                (matchStat.getRebound50s() != null && matchStat.getRebound50s() < 0) ||
                (matchStat.getInside50s() != null && matchStat.getInside50s() < 0) ||
                (matchStat.getClearances() != null && matchStat.getClearances() < 0) ||
                (matchStat.getClangers() != null && matchStat.getClangers() < 0) ||
                (matchStat.getFreeKicksFor() != null && matchStat.getFreeKicksFor() < 0) ||
                (matchStat.getFreeKicksAgainst() != null && matchStat.getFreeKicksAgainst() < 0) ||
                (matchStat.getBrownlowVotes() != null && matchStat.getBrownlowVotes() < 0) ||
                (matchStat.getContestedPossessions() != null && matchStat.getContestedPossessions() < 0) ||
                (matchStat.getUncontestedPossessions() != null && matchStat.getUncontestedPossessions() < 0) ||
                (matchStat.getContestedMarks() != null && matchStat.getContestedMarks() < 0) ||
                (matchStat.getMarksInside50() != null && matchStat.getMarksInside50() < 0) ||
                (matchStat.getOnePercenters() != null && matchStat.getOnePercenters() < 0) ||
                (matchStat.getBounces() != null && matchStat.getBounces() < 0) ||
                (matchStat.getGoalAssist() != null && matchStat.getGoalAssist() < 0) ||
                (matchStat.getPercentageOfGamePlayed() != null && matchStat.getPercentageOfGamePlayed() < 0)
        )
            throw new InvalidMatchStatArgumentException("Numerical value is negative");

    }
}
