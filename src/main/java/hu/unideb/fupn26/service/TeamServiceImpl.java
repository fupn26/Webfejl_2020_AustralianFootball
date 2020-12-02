package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.TeamDao;
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
public class TeamServiceImpl implements TeamService{

    private final TeamDao teamDao;

    @Override
    public Collection<Team> getAllAddress() {
        return teamDao.readAll();
    }

    @Override
    public void recordAddress(Team team) throws TeamAlreadyExistsException {
        teamDao.createTeam(team);
    }

    @Override
    public void deleteTeam(Team team) throws UnknownTeamException {
        teamDao.deleteTeam(team);
    }
}
