package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.MatchStat;

import java.util.Collection;

public interface MatchStatDao {

    void createMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException, MatchStatAlreadyExistsException;
    Collection<MatchStat> readAll();
    void updateMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException, UnknownMatchStatException;
    void deleteMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException;
}
