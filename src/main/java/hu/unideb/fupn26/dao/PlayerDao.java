package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.exception.TeamAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Player;
import hu.unideb.fupn26.model.Team;

import java.util.Collection;

public interface PlayerDao {

    void createPlayer(Player player);
    Collection<Player> readAll();
    void deletePlayer(Player player);
}
