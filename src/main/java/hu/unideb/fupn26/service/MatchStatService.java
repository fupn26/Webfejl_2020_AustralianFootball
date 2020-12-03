package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.MatchStat;

import java.util.Collection;

public interface MatchStatService {
    Collection<MatchStat> getAllMatchStat();
    Collection<MatchStat> getAllMatchStatByPlayer(String firstName, String lastName);
    Collection<MatchStat> getAllMatchStatByTeam(String teamName);

    void recordMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException;
    void deleteMatchStat(MatchStat matchStat) throws UnknownPlayerException, UnknownMatchException;
}
