package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.exception.MatchAlreadyExistsException;
import hu.unideb.fupn26.exception.MatchSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;
import hu.unideb.fupn26.model.MatchStat;

import java.util.Collection;

public interface MatchDao {

    void createMatch(Match match) throws UnknownTeamException, MatchAlreadyExistsException;
    void updateMatch(Match match) throws UnknownMatchException;
    Collection<Match> readAll();
    void deleteMatch(Match match) throws UnknownMatchException, MatchSqlIntegrityException;
}
