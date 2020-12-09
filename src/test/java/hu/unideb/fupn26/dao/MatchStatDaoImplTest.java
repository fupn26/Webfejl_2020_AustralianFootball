package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.*;
import hu.unideb.fupn26.dao.repository.MatchRepository;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.PlayerRepository;
import hu.unideb.fupn26.dao.repository.TeamRepository;
import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MatchStatDaoImplTest {

    @Spy
    @InjectMocks
    private MatchStatDaoImpl dao;
    @Mock
    private MatchStatRepository matchStatRepository;
    @Mock
    private MatchRepository matchRepository;
    @Mock
    private TeamRepository teamRepository;
    @Mock
    private PlayerRepository playerRepository;

    @Test
    void testCreateMatchStat() throws MatchStatAlreadyExistsException, UnknownPlayerException, UnknownTeamException, UnknownMatchException {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));
        when(teamRepository.findById(any())).thenReturn(Optional.of(getTeamEntity()));
        when(matchStatRepository.findById(any())).thenReturn(Optional.empty());

        dao.createMatchStat(getMatchStat());

        verify(matchStatRepository, times(1)).save(any());
    }

    @Test
    void testCreateMatchStatWithUnknownMatch() {

        when(matchRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownMatchException.class, () -> {
            dao.createMatchStat(getMatchStat());
        });
    }

    @Test
    void testCreateMatchStatWithUnknownPlayer() {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownPlayerException.class, () -> {
           dao.createMatchStat(getMatchStat());
        });
    }

    @Test
    void testCreateMatchStatWithUnknownTeam() {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));
        when(teamRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownTeamException.class, () -> {
           dao.createMatchStat(getMatchStat());
        });
    }

    @Test
    void testCreateMatchStatWithMatchStatAlreadyExists() {
        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));
        when(teamRepository.findById(any())).thenReturn(Optional.of(getTeamEntity()));
        when(matchStatRepository.findById(any())).thenReturn(Optional.of(getMatchStatEntity()));

        assertThrows(MatchStatAlreadyExistsException.class, () -> {
           dao.createMatchStat(getMatchStat());
        });

    }

    @Test
    void testReadAll() {

        when(matchStatRepository.findAll()).thenReturn(getMatchStatEntities());

        Collection<MatchStat> actual = dao.readAll();

        assertThat(getMatchStats(), is(actual));
    }

    @Test
    void testUpdateMatchStat() throws UnknownPlayerException, UnknownTeamException, UnknownMatchException, UnknownMatchStatException {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));
        when(teamRepository.findById(any())).thenReturn(Optional.of(getTeamEntity()));
        when(matchStatRepository.findById(any())).thenReturn(Optional.of(getMatchStatEntity()));

        dao.updateMatchStat(getMatchStat());

        verify(matchStatRepository, times(1)).save(any());
    }

    @Test
    void testUpdateMatchStatWithUnknownMatch() {

        when(matchRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownMatchException.class, () -> {
            dao.updateMatchStat(getMatchStat());
        });
    }

    @Test
    void testUpdateMatchStatWithUnknownPlayer() {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownPlayerException.class, () -> {
            dao.updateMatchStat(getMatchStat());
        });
    }

    @Test
    void testUpdateMatchStatWithUnknownTeam() {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));
        when(teamRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownTeamException.class, () -> {
            dao.updateMatchStat(getMatchStat());
        });
    }

    @Test
    void testUpdateMatchStatWithUnknownMatchStat() {

        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));
        when(teamRepository.findById(any())).thenReturn(Optional.of(getTeamEntity()));
        when(matchStatRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownMatchStatException.class, () -> {
            dao.updateMatchStat(getMatchStat());
        });
    }


    @Test
    void testDeleteMatchStat() throws UnknownPlayerException, UnknownMatchException {
        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));

        dao.deleteMatchStat(getMatchStat());

        verify(matchStatRepository, times(1)).deleteById(any());
    }

    @Test
    void testDeleteMatchStatWithUnknownMatch() {
        when(matchRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownMatchException.class, () -> {
            dao.deleteMatchStat(getMatchStat());
        });
    }

    @Test
    void testDeleteMatchStatWithUnknownPlayer() {
        when(matchRepository.findById(any())).thenReturn(Optional.of(getMatchEntity()));
        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownPlayerException.class, () -> {
            dao.deleteMatchStat(getMatchStat());
        });
    }

    private MatchStat getMatchStat() {
        return MatchStat.builder()
                .matchId("2020_EF_10_11")
                .playerId(10001)
                .teamId(10)
                .goals(1)
                .behinds(20)
                .build();
    }

    private MatchStatEntity getMatchStatEntity() {
        return MatchStatEntity.builder()
                .id(new MatchStatId(getMatchEntity(), getPlayerEntity()))
                .team(getTeamEntity())
                .goals(1)
                .behinds(20)
                .build();
    }

    private Match getMatch() {
        return Match.builder()
                .id("2020_EF_10_11")
                .team1(getTeamEntity().getName())
                .team2(getTeamEntity().getName())
                .build();
    }

    private MatchEntity getMatchEntity() {
        return MatchEntity.builder()
                .id("2020_EF_10_11")
                .team1(getTeamEntity())
                .team2(getTeamEntity())
                .build();
    }

    private Collection<MatchStat> getMatchStats() {
        return Arrays.asList(
                MatchStat.builder()
                        .matchId("2020_EF_10_11")
                        .playerId(10001)
                        .teamId(10)
                        .goals(1)
                        .behinds(20)
                        .build(),
                MatchStat.builder()
                        .matchId("2020_EF_10_11")
                        .playerId(10001)
                        .teamId(10)
                        .goals(1)
                        .behinds(20)
                        .build()
        );
    }

    private Collection<MatchStatEntity> getMatchStatEntities() {
        return Arrays.asList(
                MatchStatEntity.builder()
                        .id(new MatchStatId(getMatchEntity(), getPlayerEntity()))
                        .team(getTeamEntity())
                        .goals(1)
                        .behinds(20)
                        .build(),
                MatchStatEntity.builder()
                        .id(new MatchStatId(getMatchEntity(), getPlayerEntity()))
                        .team(getTeamEntity())
                        .goals(1)
                        .behinds(20)
                        .build()
        );
    }

    private TeamEntity getTeamEntity() {
        return TeamEntity.builder()
                .id(10)
                .name("Best Team")
                .build();
    }

    private Player getPlayer() {
        return new Player(
                10001
        );
    }

    private PlayerEntity getPlayerEntity() {
        return PlayerEntity.builder()
                .id(10001)
                .build();
    }
}