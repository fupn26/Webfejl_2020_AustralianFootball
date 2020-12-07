package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.TeamEntity;
import hu.unideb.fupn26.dao.repository.MatchRepository;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.TeamRepository;
import hu.unideb.fupn26.exception.InvalidTeamNameException;
import hu.unideb.fupn26.exception.TeamAlreadyExistsException;
import hu.unideb.fupn26.exception.TeamSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamDaoImpl implements TeamDao{

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private final MatchStatRepository matchStatRepository;

    @Override
    public void createTeam(Team team) throws TeamAlreadyExistsException {

        if (teamRepository.findByName(team.getTeamName()).isPresent())
            throw new TeamAlreadyExistsException(String.format("Team already exists: %s", team));

        TeamEntity teamEntity = TeamEntity.builder()
                .name(team.getTeamName())
                .build();

        log.info("TeamEntity: {}", teamEntity);
        try {
            teamRepository.save(teamEntity);
        } catch (Exception e) {
            log.error("Can't create new team: {}", e.getMessage());
        }
    }

    @Override
    public void updateTeam(Team team) throws UnknownTeamException {

        Optional<TeamEntity> teamEntity = teamRepository.findById(team.getId());

        if (teamEntity.isEmpty())
            throw new UnknownTeamException(String.format("Team not found: %s", team));

        TeamEntity newTeamEntity = teamEntity.get();
        newTeamEntity.setName(team.getTeamName());

        try {
            teamRepository.save(newTeamEntity);
        } catch (Exception e) {
            log.error("Can't update team: {}", e.getMessage());
        }
    }

    @Override
    public Collection<Team> readAll() {
        return StreamSupport.stream(teamRepository.findAll().spliterator(), false)
                .map(entity -> new Team(entity.getId(), entity.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTeam(Team team) throws UnknownTeamException, TeamSqlIntegrityException {
        Optional<TeamEntity> teamEntity;

        if (team.getId() != null)
            teamEntity = teamRepository.findById(team.getId());
        else
            teamEntity = teamRepository.findByName(team.getTeamName());

        if (teamEntity.isEmpty())
            throw new UnknownTeamException(String.format("Team not found: %s", team));

        if (!matchRepository.findByTeam1OrTeam2(teamEntity.get(), teamEntity.get()).isEmpty())
            throw new TeamSqlIntegrityException("Team can't be deleted because it is referenced in Match table.");

        if (!matchStatRepository.findByTeam(teamEntity.get()).isEmpty())
            throw new TeamSqlIntegrityException("Team can't be deleted because it is referenced in MatchStat table.");

        teamRepository.delete(teamEntity.get());
    }
}
