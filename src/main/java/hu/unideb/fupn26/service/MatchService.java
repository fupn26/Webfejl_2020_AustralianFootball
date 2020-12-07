package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.InvalidMatchArgumentException;
import hu.unideb.fupn26.exception.MatchSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;

import java.util.Collection;

public interface MatchService {

    Collection<Match> getAllMatch();
    Collection<Match> getAllMatchByTeam(String teamName);

    void recordMatch(Match match) throws UnknownTeamException, InvalidMatchArgumentException;
    void deleteMatch(Match match) throws UnknownMatchException, MatchSqlIntegrityException;
}
