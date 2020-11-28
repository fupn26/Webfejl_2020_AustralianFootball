package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.model.Match;
import hu.unideb.fupn26.model.MatchStat;

import java.util.Collection;

public interface MatchDao {

    void createMatch(Match match);
    Collection<MatchStat> readAll();
    void deleteMatch(Match match);
}
