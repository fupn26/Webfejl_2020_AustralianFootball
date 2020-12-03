package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.MatchDao;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
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
    public void recordMatch(Match match) throws UnknownTeamException {
        matchDao.createMatch(match);
    }

    @Override
    public void deleteMatch(Match match) throws UnknownTeamException, UnknownMatchException {
        matchDao.deleteMatch(match);

    }
}
