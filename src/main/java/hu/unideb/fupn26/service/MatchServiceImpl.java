package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.MatchDao;
import hu.unideb.fupn26.exception.InvalidMatchArgumentException;
import hu.unideb.fupn26.exception.MatchSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService{

    private final MatchDao matchDao;

    @Override
    public Collection<Match> getAllMatch() {
        return matchDao.readAll();
    }

    @Override
    public Collection<Match> getAllMatchByTeam(String teamName) {
        return matchDao.readAll().stream()
                .filter(match -> match.getTeam1().equals(teamName) ||
                        match.getTeam2().equals(teamName)
                )
                .collect(Collectors.toList());
    }

    @Override
    public void recordMatch(Match match) throws UnknownTeamException, InvalidMatchArgumentException {
        validateMatch(match);

        matchDao.createMatch(match);
    }

    @Override
    public void deleteMatch(Match match) throws UnknownMatchException, MatchSqlIntegrityException {
        matchDao.deleteMatch(match);
    }

    private void validateMatch(Match match) throws InvalidMatchArgumentException {
        if (match.getTeam1Score() < 0 ||
                match.getTeam2Score() < 0 ||
                (match.getAttendants() != null && match.getAttendants() < 0) ||
                (match.getMargin() != null && match.getMargin() < 0) ||
                (match.getHomeQ1Goals() != null && match.getHomeQ1Goals() < 0) ||
                (match.getHomeQ2Goals() != null && match.getHomeQ2Goals() < 0) ||
                (match.getHomeQ3Goals() != null && match.getHomeQ3Goals() < 0) ||
                (match.getHomeQ4Goals() != null && match.getHomeQ4Goals() < 0) ||
                (match.getHomeExtraTimeGoals() != null && match.getHomeExtraTimeGoals() < 0) ||
                (match.getHomeQ1Behinds() != null && match.getHomeQ1Behinds() < 0) ||
                (match.getHomeQ2Behinds() != null && match.getHomeQ2Behinds() < 0) ||
                (match.getHomeQ3Behinds() != null && match.getHomeQ3Behinds() < 0) ||
                (match.getHomeQ4Behinds() != null && match.getHomeQ4Behinds() < 0) ||
                (match.getHomeExtraTimeBehinds() != null && match.getHomeExtraTimeBehinds() < 0) ||
                (match.getAwayQ1Goals() != null && match.getAwayQ1Goals() < 0) ||
                (match.getAwayQ2Goals() != null && match.getAwayQ2Goals() < 0) ||
                (match.getAwayQ3Goals() != null && match.getAwayQ3Goals() < 0) ||
                (match.getAwayQ4Goals() != null && match.getAwayQ4Goals() < 0) ||
                (match.getAwayExtraTimeGoals() != null && match.getAwayExtraTimeGoals() < 0) ||
                (match.getAwayQ1Behinds() != null && match.getAwayQ1Behinds() < 0) ||
                (match.getAwayQ2Behinds() != null && match.getAwayQ2Behinds() < 0) ||
                (match.getAwayQ3Behinds() != null && match.getAwayQ3Behinds() < 0) ||
                (match.getAwayQ4Behinds() != null && match.getAwayQ4Behinds() < 0) ||
                (match.getAwayExtraTimeBehinds() != null && match.getAwayExtraTimeBehinds() < 0)
        )
            throw  new InvalidMatchArgumentException("Negative numerical value");

        if (match.getSeason() < 1858) {
            throw new InvalidMatchArgumentException("Season is lesser than 1858");
        }

        if (Objects.equals(match.getTeam1Score(), match.getTeam2Score())) {
            throw new InvalidMatchArgumentException("Equal team scores");
        }

        if (match.getTeam1Location().equals(match.getTeam2Location())) {
            throw new InvalidMatchArgumentException("Equal team locations");
        }

        if (match.getTeam1().equals(match.getTeam2())) {
            throw new InvalidMatchArgumentException("Equal team names");
        }
    }
}
