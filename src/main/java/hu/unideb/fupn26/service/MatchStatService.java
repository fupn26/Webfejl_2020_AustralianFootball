package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.MatchStat;

import java.util.Collection;

public interface MatchStatService {
    Collection<MatchStat> getAllMatchStat();
    Collection<MatchStat> getAllMatchStatByPlayer(Integer playerId);
    Collection<MatchStat> getAllMatchStatByTeam(Integer teamId);

    void recordMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException, InvalidMatchStatArgumentException, MatchStatAlreadyExists;
    void updateMatchStat(MatchStat matchStat) throws InvalidMatchStatArgumentException, UnknownPlayerException, UnknownTeamException, UnknownMatchException, UnknownMatchStatException;
    void deleteMatchStat(MatchStat matchStat) throws UnknownPlayerException, UnknownMatchException, InvalidMatchStatArgumentException;
}
