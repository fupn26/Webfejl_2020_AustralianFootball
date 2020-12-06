package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.exception.InvalidTeamNameException;
import hu.unideb.fupn26.exception.TeamAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Team;

import java.util.Collection;

public interface TeamDao {

    void createTeam(Team team) throws TeamAlreadyExistsException, InvalidTeamNameException;
    Collection<Team> readAll();
    void deleteTeam(Team team) throws UnknownTeamException;
}
