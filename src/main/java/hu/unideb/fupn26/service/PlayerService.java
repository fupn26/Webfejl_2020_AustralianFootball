package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.InvalidPlayerArgumentException;
import hu.unideb.fupn26.exception.PlayerAlreadyExistsException;
import hu.unideb.fupn26.exception.PlayerSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.model.Player;

import java.util.Collection;

public interface PlayerService {

    Collection<Player> getAllPlayer();

    void recordPlayer(Player player) throws InvalidPlayerArgumentException;
    void updatePlayer(Player player) throws InvalidPlayerArgumentException, UnknownPlayerException;
    void deletePlayer(Player player) throws UnknownPlayerException, PlayerSqlIntegrityException, InvalidPlayerArgumentException;
}
