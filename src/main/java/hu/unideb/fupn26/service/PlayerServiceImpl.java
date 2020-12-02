package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.PlayerDao;
import hu.unideb.fupn26.exception.PlayerAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService{

    private final PlayerDao playerDao;

    @Override
    public Collection<Player> getAllPlayer() {
        return playerDao.readAll();
    }

    @Override
    public void recordPlayer(Player player) throws PlayerAlreadyExistsException {
        playerDao.createPlayer(player);
    }

    @Override
    public void deletePlayer(Player player) throws UnknownPlayerException {
        playerDao.deletePlayer(player);
    }
}
