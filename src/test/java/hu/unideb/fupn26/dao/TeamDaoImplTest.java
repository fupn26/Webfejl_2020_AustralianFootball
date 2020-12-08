package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.MatchEntity;
import hu.unideb.fupn26.dao.entity.MatchStatEntity;
import hu.unideb.fupn26.dao.entity.MatchStatId;
import hu.unideb.fupn26.dao.entity.TeamEntity;
import hu.unideb.fupn26.dao.repository.MatchRepository;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.TeamRepository;
import hu.unideb.fupn26.exception.TeamAlreadyExistsException;
import hu.unideb.fupn26.exception.TeamSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamDaoImplTest {

    @Spy
    @InjectMocks
    private TeamDaoImpl dao;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private MatchStatRepository matchStatRepository;

    @Test
    void testCreateTeam() throws TeamAlreadyExistsException {

        dao.createTeam(getTeamWithoutId());

        verify(teamRepository, times(1)).save(any());
    }

    @Test
    void testCreateTeamWithAlreadyExistingTeam() {

        when(teamRepository.findByName(any())).
                thenReturn(Optional.of(getTeamEntity()));

        assertThrows(TeamAlreadyExistsException.class, () -> {
            dao.createTeam(getTeamWithoutId());
        });
    }

    @Test
    void testUpdateTeam() throws UnknownTeamException {

        when(teamRepository.findById(any())).thenReturn(Optional.of(getTeamEntity()));

        dao.updateTeam(getTeamWithoutId());

        verify(teamRepository, times(1)).save(any());
    }

    @Test
    void testUpdateTeamWithUnknownTeam() {

        when(teamRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownTeamException.class, () -> {
           dao.updateTeam(getTeamWithoutId());
        });

    }

    @Test
    void testReadAll() {

        when(teamRepository.findAll()).thenReturn(getDefaultTeamEntities());

        Collection<Team> actual = dao.readAll();

        assertThat(getDefaultTeams(), is(actual));
    }

    @Test
    void testDeleteTeam() throws TeamSqlIntegrityException, UnknownTeamException {

        when(teamRepository.findByName(any())).thenReturn(Optional.of(getTeamEntity()));
        when(matchRepository.findByTeam1OrTeam2(any(), any())).thenReturn(new ArrayList<>());
        when(matchStatRepository.findByTeam(any())).thenReturn(new ArrayList<>());

        dao.deleteTeam(getTeamWithoutId());

        verify(teamRepository, times(1)).delete(any());
    }

    @Test
    void testDeleteTeamWithoutTeamId() throws TeamSqlIntegrityException, UnknownTeamException {
        when(teamRepository.findByName(any())).thenReturn(Optional.of(getTeamEntity()));
        when(matchRepository.findByTeam1OrTeam2(any(), any())).thenReturn(new ArrayList<>());
        when(matchStatRepository.findByTeam(any())).thenReturn(new ArrayList<>());

        dao.deleteTeam(getTeamWithoutId());

        verify(teamRepository, times(1)).findByName(any());
    }

    @Test
    void testDeleteTeamWithTeamId() throws TeamSqlIntegrityException, UnknownTeamException {
        when(teamRepository.findById(any())).thenReturn(Optional.of(getTeamEntity()));
        when(matchRepository.findByTeam1OrTeam2(any(), any())).thenReturn(new ArrayList<>());
        when(matchStatRepository.findByTeam(any())).thenReturn(new ArrayList<>());

        dao.deleteTeam(getTeamWithId());

        verify(teamRepository, times(1)).findById(any());
    }

    @Test
    void testDeleteTeamWithUnknownTeam() {
        when(teamRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownTeamException.class, () -> {
            dao.deleteTeam(getTeamWithId());
        });
    }

    @Test
    void testDeleteTeamWithTeamReferencedInMatchTable() {
        when(teamRepository.findById(any())).thenReturn(Optional.of(getTeamEntity()));
        when(matchRepository.findByTeam1OrTeam2(any(), any())).thenReturn(getMatchEntities());

        assertThrows(TeamSqlIntegrityException.class, () -> {
            dao.deleteTeam(getTeamWithId());
        });
    }

    @Test
    void testDeleteTeamWithTeamReferencedInMatchStatTable() {
        when(teamRepository.findById(any())).thenReturn(Optional.of(getTeamEntity()));
        when(matchRepository.findByTeam1OrTeam2(any(), any())).thenReturn(new ArrayList<>());
        when(matchStatRepository.findByTeam(any())).thenReturn(getMatchStatEntities());

        assertThrows(TeamSqlIntegrityException.class, () -> {
            dao.deleteTeam(getTeamWithId());
        });

    }

    private Team getTeamWithoutId() {
        return new Team("Mockito FC");
    }

    private Team getTeamWithId() {
        return new Team(10, "Mockito FC");
    }

    private TeamEntity getTeamEntity() {
        return new TeamEntity(20, "Non Mockito FC");
    }

    private Collection<TeamEntity> getDefaultTeamEntities() {
        return Arrays.asList(
            new TeamEntity(
                    10,
                    "Alma"
            ),
            new TeamEntity(
                    11,
                    "Barack"
            ),
            new TeamEntity(
                    12,
                    "Citrom"
            )
        );
    }

    private Collection<Team> getDefaultTeams() {
        return Arrays.asList(
                new Team(
                        10,
                        "Alma"
                ),
                new Team(
                        11,
                        "Barack"
                ),
                new Team(
                        12,
                        "Citrom"
                )
        );
    }

    private Collection<MatchEntity> getMatchEntities() {
        return  Arrays.asList(
                MatchEntity.builder()
                    .id("2020_EF_101_102")
                    .build(),
                MatchEntity.builder()
                    .id("2020_EF_102_101")
                    .build()
        );
    }

    private Collection<MatchStatEntity> getMatchStatEntities() {
        return Arrays.asList(
                MatchStatEntity.builder()
                    .id(new MatchStatId(null, null))
                    .build(),
                MatchStatEntity.builder()
                    .id(new MatchStatId(null, null))
                    .build()
        );
    }

}