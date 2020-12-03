package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;

import java.util.Collection;

public interface MatchService {

    Collection<Match> getAllMatch();
    Collection<Match> getAllMatchByTeam(String teamName);

    void recordMatch(Match match) throws UnknownTeamException;
    void deleteMatch(Match match) throws UnknownTeamException, UnknownMatchException;
}
