package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.PlayerDao;
import hu.unideb.fupn26.exception.InvalidPlayerArgumentException;
import hu.unideb.fupn26.exception.PlayerSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.model.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @InjectMocks
    private PlayerServiceImpl service;
    @Mock
    private PlayerDao dao;

    @Test
    void testGetAllPlayer() {

        when(dao.readAll()).thenReturn(getPlayers());

        Collection<Player> actual = service.getAllPlayer();

        assertThat(getPlayers(), is(actual));
    }

    @Test
    void testRecordPlayer() throws InvalidPlayerArgumentException {

        service.recordPlayer(getValidPlayer());

        verify(dao, times(1)).createPlayer(any());
    }

    @Test
    void testRecordPlayerWithWeightNull() {

        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.recordPlayer(getPlayerWithWeightNull());
        });
    }

    @Test
    void testRecordPlayerWithWeightNegative() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.recordPlayer(getPlayerWithWeightNegative());
        });
    }

    @Test
    void testRecordPlayerWithHeightNull() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.recordPlayer(getPlayerWithHeightNull());
        });
    }

    @Test
    void testRecordPlayerWithHeightNegative() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.recordPlayer(getPlayerWithHeightNegative());
        });
    }

    @Test
    void testRecordPlayerWithFirstNameNull() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.recordPlayer(getPlayerWithFirstNameNull());
        });
    }

    @Test
    void testRecordPlayerWithFirstNameBlank() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.recordPlayer(getPlayerWithFirstNameBlank());
        });
    }

    @Test
    void testRecordPlayerWithLastNameNull() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.recordPlayer(getPlayerWithLastNameNull());
        });
    }

    @Test
    void testRecordPlayerWithLastNameBlank() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.recordPlayer(getPlayerWithLastNameBlank());
        });
    }

    @Test
    void testRecordPlayerWithBirthDateNull() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.recordPlayer(getPlayerWithBirthDateNull());
        });
    }
//##################################################################
    @Test
    void testUpdatePlayer() throws InvalidPlayerArgumentException, UnknownPlayerException {

        service.updatePlayer(getValidPlayer());

        verify(dao, times(1)).updatePlayer(any());
    }

    @Test
    void testUpdatePlayerWithIdNull() {

        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithIdNull());
        });
    }

    @Test
    void testUpdatePlayerWithWeightNull() {

        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithWeightNull());
        });
    }

    @Test
    void testUpdatePlayerWithWeightNegative() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithWeightNegative());
        });
    }

    @Test
    void testUpdatePlayerWithHeightNull() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithHeightNull());
        });
    }

    @Test
    void testUpdatePlayerWithHeightNegative() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithHeightNegative());
        });
    }

    @Test
    void testUpdatePlayerWithFirstNameNull() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithFirstNameNull());
        });
    }

    @Test
    void testUpdatePlayerWithFirstNameBlank() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithFirstNameBlank());
        });
    }

    @Test
    void testUpdatePlayerWithLastNameNull() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithLastNameNull());
        });
    }

    @Test
    void testUpdatePlayerWithLastNameBlank() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithLastNameBlank());
        });
    }

    @Test
    void testUpdatePlayerWithBirthDateNull() {
        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.updatePlayer(getPlayerWithBirthDateNull());
        });
    }

    @Test
    void testUpdatePlayerWithUnknownPlayer() throws UnknownPlayerException {

        doThrow(UnknownPlayerException.class).when(dao).updatePlayer(getValidPlayer());

        assertThrows(UnknownPlayerException.class, () -> {
            service.updatePlayer(getValidPlayer());
        });
    }

    @Test
    void testDeletePlayer() throws UnknownPlayerException, PlayerSqlIntegrityException, InvalidPlayerArgumentException {

        service.deletePlayer(getValidPlayer());

        verify(dao, times(1)).deletePlayer(any());
    }

    @Test
    void testDeletePlayerWithIdNull() {

        assertThrows(InvalidPlayerArgumentException.class, () -> {
            service.deletePlayer(getPlayerWithIdNull());
        });
    }

    @Test
    void testDeletePlayerWithUnknownPlayer() throws PlayerSqlIntegrityException, UnknownPlayerException {

        doThrow(UnknownPlayerException.class).when(dao).deletePlayer(any());

        assertThrows(UnknownPlayerException.class, () -> {
            service.deletePlayer(getValidPlayer());
        });
    }

    @Test
    void testDeletePlayerWithPlayerReferencedInOtherTable() throws PlayerSqlIntegrityException, UnknownPlayerException {

        doThrow(PlayerSqlIntegrityException.class).when(dao).deletePlayer(any());

        assertThrows(PlayerSqlIntegrityException.class, () -> {
            service.deletePlayer(getValidPlayer());
        });
    }

    private Player getValidPlayer() {
        return new Player(
                10,
                LocalDateTime.MIN,
                "Elek",
                "Rend",
                170,
                100
        );
    }

    private Player getPlayerWithWeightNull() {
        return new Player(
                10,
                LocalDateTime.MIN,
                "Elek",
                "Rend",
                170,
                null
        );
    }

    private Player getPlayerWithWeightNegative() {
        return new Player(
                10,
                LocalDateTime.MIN,
                "Elek",
                "Rend",
                170,
                -1
        );
    }

    private Player getPlayerWithHeightNull() {
        return new Player(
                10,
                LocalDateTime.MIN,
                "Elek",
                "Rend",
                null,
                100
        );
    }

    private Player getPlayerWithHeightNegative() {
        return new Player(
                10,
                LocalDateTime.MIN,
                "Elek",
                "Rend",
                -1,
                100
        );
    }

    private Player getPlayerWithFirstNameNull() {
        return new Player(
                10,
                LocalDateTime.MIN,
                null,
                "Rend",
                170,
                100
        );
    }

    private Player getPlayerWithFirstNameBlank() {
        return new Player(
                10,
                LocalDateTime.MIN,
                "",
                "Rend",
                170,
                100
        );
    }

    private Player getPlayerWithLastNameNull() {
        return new Player(
                10,
                LocalDateTime.MIN,
                "Elek",
                null,
                170,
                100
        );
    }

    private Player getPlayerWithLastNameBlank() {
        return new Player(
                10,
                LocalDateTime.MIN,
                "Elek",
                "",
                170,
                100
        );
    }

    private Player getPlayerWithBirthDateNull() {
        return new Player(
                10,
                null,
                "Elek",
                "Rend",
                170,
                100
        );
    }

    private Player getPlayerWithIdNull() {
        return new Player(
                null,
                LocalDateTime.MIN,
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
                        LocalDateTime.MIN,
                        "Elek",
                        "Rend",
                        170,
                        100
                ),
                new Player(
                        11,
                        LocalDateTime.MAX,
                        "Zita",
                        "Para",
                        150,
                        40
                )
        );
    }

}