package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.MatchStat;
import hu.unideb.fupn26.model.Player;
import hu.unideb.fupn26.model.Team;

import java.util.Collection;

public interface MatchStatService {
    Collection<MatchStat> getAllMatchStat();
    Collection<MatchStat> getAllMatchStatByPlayer(Player player);
    Collection<MatchStat> getAllMatchStatByTeam(Team team);

    void recordMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException;
    void deleteMatchStat(MatchStat matchStat) throws UnknownPlayerException, UnknownMatchException;
}
