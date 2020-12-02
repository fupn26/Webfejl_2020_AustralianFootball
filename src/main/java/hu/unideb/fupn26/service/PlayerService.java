package hu.unideb.fupn26.service;

import hu.unideb.fupn26.exception.PlayerAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.model.Player;

import java.util.Collection;

public interface PlayerService {

    Collection<Player> getAllPlayer();

    void recordPlayer(Player player) throws PlayerAlreadyExistsException;
    void deletePlayer(Player player) throws UnknownPlayerException;
}
