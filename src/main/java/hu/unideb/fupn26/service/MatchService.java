package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.Match;

import java.util.Collection;

public interface MatchService {

    Collection<Match> getAllMatch();
    Collection<Match> getAllMatchByTeam(String teamName);

    void recordMatch(Match match) throws UnknownTeamException, InvalidMatchArgumentException, MatchAlreadyExistsException;
    void updateMatch(Match match) throws InvalidMatchArgumentException, UnknownMatchException;
    void deleteMatch(Match match) throws UnknownMatchException, MatchSqlIntegrityException;
}
