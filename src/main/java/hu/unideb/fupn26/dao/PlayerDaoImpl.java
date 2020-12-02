package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.PlayerEntity;
import hu.unideb.fupn26.dao.repository.PlayerRepository;
import hu.unideb.fupn26.exception.PlayerAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.model.Player;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlayerDaoImpl implements PlayerDao{

    private final PlayerRepository playerRepository;

    @Override
    public void createPlayer(Player player) throws PlayerAlreadyExistsException {
        if (playerRepository.findByBirthDateAndFirstNameAndLastNameAndHeightAndWeight(
                Timestamp.valueOf(LocalDate.of(player.getYearOfBirth(),
                        player.getMonthOfBirth(),
                        player.getDayOfBirth()).atStartOfDay()),
                player.getFirstName(),
                player.getLastName(),
                player.getHeight(),
                player.getWeight()
            ).isPresent()
        )
            throw new PlayerAlreadyExistsException(String.format("Player already exists: %s", player));

        PlayerEntity playerEntity = PlayerEntity.builder()
                .birthDate(Timestamp.valueOf(LocalDate.of(player.getYearOfBirth(),
                                        player.getMonthOfBirth(),
                                        player.getDayOfBirth()).atStartOfDay()))
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
    public Collection<Player> readAll() {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), false)
                .map(entity -> new Player(
                        entity.getBirthDate().toLocalDateTime().getYear(),
                        entity.getBirthDate().toLocalDateTime().getMonthValue(),
                        entity.getBirthDate().toLocalDateTime().getDayOfMonth(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getHeight(),
                        entity.getWeight()
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public void deletePlayer(Player player) throws UnknownPlayerException {
        Optional<PlayerEntity> playerEntity = playerRepository.findByBirthDateAndFirstNameAndLastNameAndHeightAndWeight(
                Timestamp.valueOf(LocalDate.of(player.getYearOfBirth(),
                        player.getMonthOfBirth(),
                        player.getDayOfBirth()).atStartOfDay()),
                player.getFirstName(),
                player.getLastName(),
                player.getHeight(),
                player.getWeight()
        );

        if (playerEntity.isEmpty())
            throw new UnknownPlayerException(String.format("Player not found: %s", player));

        playerRepository.delete(playerEntity.get());
    }
}
