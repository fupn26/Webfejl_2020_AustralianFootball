package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.model.MatchStat;

import java.util.Collection;

public interface MatchStatDao {

    void createMatchStat(MatchStat matchStat) throws UnknownMatchException;
    Collection<MatchStat> readAll();
    void deleteMatchStat(MatchStat matchStat);
}
