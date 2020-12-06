package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.TeamDao;
import hu.unideb.fupn26.exception.InvalidTeamNameException;
import hu.unideb.fupn26.exception.TeamAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService {

    private final TeamDao teamDao;

    @Override
    public Collection<Team> getAllTeam() {
        return teamDao.readAll();
    }

    @Override
    public void recordTeam(Team team) throws TeamAlreadyExistsException, InvalidTeamNameException {
        if (team.getTeamName().isEmpty() || team.getTeamName().trim().isEmpty())
            throw new InvalidTeamNameException("Team name is empty");

        teamDao.createTeam(team);
    }

    @Override
    public void updateTeam(Team team) throws UnknownTeamException, InvalidTeamNameException {
        if (team.getTeamName().isEmpty() || team.getTeamName().trim().isEmpty())
            throw new InvalidTeamNameException("Team name is empty");

        teamDao.updateTeam(team);
    }

    @Override
    public void deleteTeam(Team team) throws UnknownTeamException {
        teamDao.deleteTeam(team);
    }
}
