package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.MatchStatEntity;
import hu.unideb.fupn26.dao.entity.MatchStatId;
import hu.unideb.fupn26.dao.entity.PlayerEntity;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.PlayerRepository;
import hu.unideb.fupn26.exception.PlayerSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerDaoImplTest {

    @Spy
    @InjectMocks
    private PlayerDaoImpl dao;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private MatchStatRepository matchStatRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Test
    void testCreatePlayer() {

        dao.createPlayer(getPlayer());

        verify(playerRepository, times(1)).save(any());
    }

    @Test
    void testUpdatePlayer() throws UnknownPlayerException {

        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));

        dao.updatePlayer(getPlayer());

        verify(playerRepository, times(1)).save(any());
    }

    @Test
    void testUpdatePlayerWithUnknownPlayer() {

        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownPlayerException.class, () -> {
            dao.updatePlayer(getPlayer());
        });
    }

    @Test
    void readAll() {

        when(playerRepository.findAll()).thenReturn(getPlayerEntities());

        Collection<Player> actual = dao.readAll();

        assertThat(getPlayers(), is(actual));
    }

    @Test
    void testDeletePlayer() throws PlayerSqlIntegrityException, UnknownPlayerException {

        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));
        when(matchStatRepository.findById_Player_Id(any())).thenReturn(new ArrayList<>());

        dao.deletePlayer(getPlayer());

        verify(playerRepository, times(1)).delete(any());
    }

    @Test
    void testDeletePlayerWithUnknownPlayer() {

        when(playerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(UnknownPlayerException.class, () -> {
            dao.deletePlayer(getPlayer());
        });
    }

    @Test
    void testDeletePlayerWithPlayerReferencedInMatchStatTable() {

        when(playerRepository.findById(any())).thenReturn(Optional.of(getPlayerEntity()));
        when(matchStatRepository.findById_Player_Id(any())).thenReturn(getMatchStatEntities());

        assertThrows(PlayerSqlIntegrityException.class, () -> {
            dao.deletePlayer(getPlayer());
        });
    }

    private Player getPlayer() {
        return new Player(
                10,
                LocalDateTime.MIN,
                "Elek",
                "Rend",
                170,
                100
        );
    }

    private PlayerEntity getPlayerEntity() {
        return new PlayerEntity(
                10,
                LocalDateTime.now(),
                "Elek",
                "Rend",
                170,
                100
        );
    }

    private Collection<Player> getPlayers() {
        return Arrays.asList(
                new Player(
                        10,
                        LocalDateTime.parse("1999-01-26 11:00:15", formatter),
                        "Elek",
                        "Rend",
                        170,
                        100
                ),
                new Player(
                        11,
                        LocalDateTime.parse("1999-01-26 11:00:15", formatter),
                        "Zita",
                        "Para",
                        150,
                        40
                )
        );
    }

    private Collection<PlayerEntity> getPlayerEntities() {
        return Arrays.asList(
                new PlayerEntity(
                        10,
                        LocalDateTime.parse("1999-01-26 11:00:15", formatter),
                        "Elek",
                        "Rend",
                        170,
                        100
                ),
                new PlayerEntity(
                        11,
                        LocalDateTime.parse("1999-01-26 11:00:15", formatter),
                        "Zita",
                        "Para",
                        150,
                        40
                )
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