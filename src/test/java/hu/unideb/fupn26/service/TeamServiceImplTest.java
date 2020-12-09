package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.TeamDao;
import hu.unideb.fupn26.exception.InvalidTeamNameException;
import hu.unideb.fupn26.exception.TeamAlreadyExistsException;
import hu.unideb.fupn26.exception.TeamSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeamServiceImplTest {

    @InjectMocks
    private TeamServiceImpl service;
    @Mock
    private TeamDao dao;

    @Test
    void testGetAllTeam() {

        when(dao.readAll()).thenReturn(getTeams());

        Collection<Team> actual = service.getAllTeam();

        assertThat(getTeams(), is(actual));
    }

    @Test
    void testRecordTeam() throws InvalidTeamNameException, TeamAlreadyExistsException {

        service.recordTeam(getFullTeam());

        verify(dao, times(1)).createTeam(any());
    }

    @Test
    void testRecordTeamWithNameNull() {

        assertThrows(InvalidTeamNameException.class, () -> {
           service.recordTeam(getTeamWithNameNull());
        });
    }

    @Test
    void testRecordTeamWithNameBlank() {

        assertThrows(InvalidTeamNameException.class, () -> {
            service.recordTeam(getTeamWithNameBlank());
        });
    }

    @Test
    void testRecordTeamWithTeamAlreadyExists() throws TeamAlreadyExistsException {

        doThrow(TeamAlreadyExistsException.class).when(dao).createTeam(any());

        assertThrows(TeamAlreadyExistsException.class, () -> {
            service.recordTeam(getFullTeam());
        });
    }

    @Test
    void testUpdateTeam() throws InvalidTeamNameException, UnknownTeamException {

        service.updateTeam(getFullTeam());

        verify(dao, times(1)).updateTeam(any());
    }

    @Test
    void testUpdateTeamWithNameNull() {

        assertThrows(InvalidTeamNameException.class, () -> {
           service.updateTeam(getTeamWithNameNull());
        });
    }

    @Test
    void testUpdateTeamWithNameBlank() {

        assertThrows(InvalidTeamNameException.class, () -> {
            service.updateTeam(getTeamWithNameBlank());
        });
    }

    @Test
    void testUpdateTeamWithUnknownTeam() throws UnknownTeamException {

        doThrow(UnknownTeamException.class).when(dao).updateTeam(any());

        assertThrows(UnknownTeamException.class, () -> {
            service.updateTeam(getFullTeam());
        });
    }

    @Test
    void testDeleteTeam() throws TeamSqlIntegrityException, UnknownTeamException {

        service.deleteTeam(getFullTeam());

        verify(dao, times(1)).deleteTeam(any());
    }

    @Test
    void testDeleteTeamWithUnknownTeam() throws TeamSqlIntegrityException, UnknownTeamException {

        doThrow(UnknownTeamException.class).when(dao).deleteTeam(any());

        assertThrows(UnknownTeamException.class, () -> {
            service.deleteTeam(getFullTeam());
        });
    }

    @Test
    void testDeleteTeamWithTeamReferencedInOtherTable() throws TeamSqlIntegrityException, UnknownTeamException {

        doThrow(TeamSqlIntegrityException.class).when(dao).deleteTeam(any());

        assertThrows(TeamSqlIntegrityException.class, () -> {
            service.deleteTeam(getFullTeam());
        });
    }

    private Team getFullTeam() {
        return new Team(10, "Alma");
    };

    private Team getTeamWithNameNull() {
        return new Team(10, null);
    }

    private Team getTeamWithNameBlank() {
        return new Team(10, "");
    }

    private Collection<Team> getTeams() {
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
}