package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.MatchStat;
import hu.unideb.fupn26.model.Team;

import java.util.Collection;

public interface TeamDao {

    void createTeam(Team team);
    Collection<Team> readAll();
    void deleteMatchStat(Team team);
}
