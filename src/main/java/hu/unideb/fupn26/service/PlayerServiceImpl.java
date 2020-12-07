package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.PlayerDao;
import hu.unideb.fupn26.exception.InvalidPlayerArgumentException;
import hu.unideb.fupn26.exception.PlayerSqlIntegrityException;
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
    public void recordPlayer(Player player) throws InvalidPlayerArgumentException {
        validatePlayer(player);

        playerDao.createPlayer(player);
    }

    @Override
    public void updatePlayer(Player player) throws InvalidPlayerArgumentException, UnknownPlayerException {
        validatePlayer(player);

        if (player.getId() == null)
            throw new InvalidPlayerArgumentException("Id is null");

        playerDao.updatePlayer(player);
    }

    @Override
    public void deletePlayer(Player player) throws UnknownPlayerException, PlayerSqlIntegrityException {
        playerDao.deletePlayer(player);
    }

    private void validatePlayer(Player player) throws InvalidPlayerArgumentException {
        if (player.getWeight() == null || player.getHeight() == null ||
                player.getBirthOfDate() == null || player.getFirstName() == null ||
                player.getLastName() == null)
            throw new InvalidPlayerArgumentException("Field's value is null");
        if (player.getWeight() < 0)
            throw new InvalidPlayerArgumentException("The weight value is negative");
        if (player.getHeight() < 0)
            throw new InvalidPlayerArgumentException("The height value is negative.");
        if (player.getLastName().isBlank())
            throw new InvalidPlayerArgumentException("The last name is empty.");
        if (player.getFirstName().isBlank())
            throw new InvalidPlayerArgumentException("The first name is empty.");
    }
}
