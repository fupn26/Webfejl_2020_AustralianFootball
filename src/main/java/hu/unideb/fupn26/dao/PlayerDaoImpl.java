package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.PlayerEntity;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.PlayerRepository;
import hu.unideb.fupn26.exception.PlayerSqlIntegrityException;
import hu.unideb.fupn26.exception.TeamSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerDaoImpl implements PlayerDao{

    private final PlayerRepository playerRepository;
    private final MatchStatRepository matchStatRepository;

    @Override
    public void createPlayer(Player player) {

        PlayerEntity playerEntity = PlayerEntity.builder()
                .birthDate(player.getBirthOfDate())
                .firstName(player.getFirstName())
                .lastName(player.getLastName())
                .height(player.getHeight())
                .weight(player.getWeight())
                .build();

        log.info("PlayerEntity: {}", playerEntity);
        try {
            playerRepository.save(playerEntity);
        } catch (Exception e) {
            log.error("Can't create new player: {}", e.getMessage());
        }
    }

    @Override
    public void updatePlayer(Player player) throws UnknownPlayerException {

        Optional<PlayerEntity> playerEntity = playerRepository.findById(player.getId());

        if (playerEntity.isEmpty())
            throw new UnknownPlayerException(String.format("Player not found: %s", player));

        PlayerEntity newPlayerEntity = playerEntity.get();
        newPlayerEntity.setBirthDate(player.getBirthOfDate());
        newPlayerEntity.setFirstName(player.getFirstName());
        newPlayerEntity.setLastName(player.getLastName());
        newPlayerEntity.setHeight(player.getHeight());
        newPlayerEntity.setWeight(player.getWeight());

        log.info("PlayerEntity: {}", newPlayerEntity);
        try {
            playerRepository.save(newPlayerEntity);
        } catch (Exception e) {
            log.error("Can't update player: {}", e.getMessage());
        }
    }

    @Override
    public Collection<Player> readAll() {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .map(entity -> new Player(
                        entity.getId(),
                        entity.getBirthDate(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getHeight(),
                        entity.getWeight()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public void deletePlayer(Player player) throws UnknownPlayerException, PlayerSqlIntegrityException {

        Optional<PlayerEntity> playerEntity = playerRepository.findById(player.getId());

        if (playerEntity.isEmpty())
            throw new UnknownPlayerException(String.format("Player not found: %s", player));

        if (!matchStatRepository.findById_Player_Id(player.getId()).isEmpty())
            throw new PlayerSqlIntegrityException("Player can't be deleted because it is referenced in MatchStat table");

        playerRepository.delete(playerEntity.get());
    }
}
