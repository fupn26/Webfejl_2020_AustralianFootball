package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.MatchEntity;
import hu.unideb.fupn26.dao.entity.MatchStatEntity;
import hu.unideb.fupn26.dao.entity.TeamEntity;
import hu.unideb.fupn26.dao.repository.MatchRepository;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.TeamRepository;
import hu.unideb.fupn26.exception.MatchAlreadyExistsException;
import hu.unideb.fupn26.exception.MatchSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;
import hu.unideb.fupn26.model.MatchLocation;
import hu.unideb.fupn26.model.MatchRound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchDaoImplTest {

    @Spy
    @InjectMocks
    private MatchDaoImpl dao;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private MatchStatRepository matchStatRepository;

    @Test
    void testCreateMatch() throws UnknownTeamException, MatchAlreadyExistsException {

        when(matchRepository.findById(any())).thenReturn(Optional.empty());
        when(teamRepository.findByName(any())).thenReturn(Optional.of(getTeamEntity()));

        dao.createMatch(getMatch());

        verify(matchRepository, times(1)).save(any());
    }

    @Test
    void testCreateMatchWithUnknownTeam() {

        when(teamRepository.findByName(any())).thenReturn(Optional.empty());

        assertThrows(UnknownTeamException.class, () -> {
           dao.createMatch(getMatch());
        });
    }

    @Test
    void testCreateMatchWithMatchAlreadyExists() {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(teamRepository.findByName(any())).thenReturn(Optional.of(getTeamEntity()));

        assertThrows(MatchAlreadyExistsException.class, () -> {
            dao.createMatch(getMatch());
        });
    }

    @Test
    void testUpdateMatch() throws UnknownMatchException {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));

        dao.updateMatch(getMatch());

        verify(matchRepository, times(1)).save(any());
    }

    @Test
    void testUpdateMatchWithUnknownMatch() {

        when(matchRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownMatchException.class, () -> {
           dao.updateMatch(getMatch());
        });
    }

    @Test
    void testReadAll() {

        when(matchRepository.findAll()).thenReturn(getMatchEntities());

        Collection<Match> actual = dao.readAll();

        assertThat(getMatches(), is(actual));
    }

    @Test
    void testDeleteMatch() throws MatchSqlIntegrityException, UnknownMatchException {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(matchStatRepository.findById_Match_Id(any())).thenReturn(new ArrayList<>());

        dao.deleteMatch(getMatch());

        verify(matchRepository, times(1)).deleteById(any());
    }

    @Test
    void testDeleteMatchWithUnknownMatch() {

        when(matchRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownMatchException.class, () -> {
           dao.deleteMatch(getMatch());
        });
    }

    @Test
    void testDeleteMatchWithMatchReferencedInMatchStatTable() {
        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(matchStatRepository.findById_Match_Id(any())).thenReturn(getMatchStatEntities());

        assertThrows(MatchSqlIntegrityException.class, () -> {
            dao.deleteMatch(getMatch());
        });
    }

    private TeamEntity getTeamEntity() {
        return TeamEntity.builder().build();
    }

    private Match getMatch() {
        return Match.builder()
                .season(2020)
                .team1("Alma")
                .team2("Körte")
                .team1Score(10)
                .team2Score(20)
                .team1Location(MatchLocation.A)
                .team2Location(MatchLocation.H)
                .round(MatchRound.EF)
                .build();
    }

    private MatchEntity getMatchEntity() {
        return MatchEntity.builder()
                .id("2020_EF_101_102")
                .team1(getTeamEntity())
                .team2(getTeamEntity())
                .build();
    }

    private Collection<MatchEntity> getMatchEntities() {
        return  Arrays.asList(
                MatchEntity.builder()
                        .id("2020_EF_101_102")
                        .team1(getTeamEntity())
                        .team2(getTeamEntity())
                        .team1Location(MatchLocation.A.name().toLowerCase())
                        .team2Location(MatchLocation.H.name().toLowerCase())
                        .round(MatchRound.EF.name())
                        .build(),
                MatchEntity.builder()
                        .id("2020_EF_102_101")
                        .team1(getTeamEntity())
                        .team2(getTeamEntity())
                        .team1Location(MatchLocation.A.name().toLowerCase())
                        .team2Location(MatchLocation.H.name().toLowerCase())
                        .round(MatchRound.EF.name())
                        .build()
        );
    }

    private Collection<Match> getMatches() {
        return  Arrays.asList(
                Match.builder()
                        .id("2020_EF_101_102")
                        .team1Location(MatchLocation.A)
                        .team2Location(MatchLocation.H)
                        .round(MatchRound.EF)
                        .build(),
                Match.builder()
                        .id("2020_EF_102_101")
                        .team1Location(MatchLocation.A)
                        .team2Location(MatchLocation.H)
                        .round(MatchRound.EF)
                        .build()
        );
    }

    private Collection<MatchStatEntity> getMatchStatEntities() {
        return Arrays.asList(
                MatchStatEntity.builder().build()
        );
    }
}