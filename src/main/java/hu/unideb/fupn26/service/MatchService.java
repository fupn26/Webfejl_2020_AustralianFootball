package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;
import hu.unideb.fupn26.model.Team;

import java.util.Collection;

public interface MatchService {

    Collection<Match> getAllMatch() throws UnknownTeamException;
    Collection<Match> getAllMatchByTeam(Team team);

    void recordMatch(Match match) throws UnknownTeamException;
    void deleteMatch(Match match) throws UnknownTeamException, UnknownMatchException;
}
