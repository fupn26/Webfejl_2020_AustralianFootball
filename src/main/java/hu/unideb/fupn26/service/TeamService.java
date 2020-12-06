package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.InvalidTeamNameException;
import hu.unideb.fupn26.exception.TeamAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Team;

import java.util.Collection;

public interface TeamService {
    Collection<Team> getAllTeam();

    void recordTeam(Team team) throws TeamAlreadyExistsException, InvalidTeamNameException;
    void updateTeam(Team team) throws UnknownTeamException;
    void deleteTeam(Team team) throws UnknownTeamException;
}
