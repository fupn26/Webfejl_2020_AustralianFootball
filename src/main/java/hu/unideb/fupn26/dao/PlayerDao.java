package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.Player;
import hu.unideb.fupn26.model.Team;

import java.util.Collection;

public interface PlayerDao {

    void createPlayer(Player player);
    void updatePlayer(Player player) throws UnknownPlayerException;
    Collection<Player> readAll();
    void deletePlayer(Player player) throws UnknownPlayerException, PlayerSqlIntegrityException;
}
