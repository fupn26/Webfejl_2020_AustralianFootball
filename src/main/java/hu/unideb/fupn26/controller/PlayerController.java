package hu.unideb.fupn26.controller;

import hu.unideb.fupn26.controller.dto.PlayerDto;
import hu.unideb.fupn26.controller.dto.PlayerCreateRequestDto;
import hu.unideb.fupn26.exception.InvalidPlayerArgumentException;
import hu.unideb.fupn26.exception.PlayerAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.model.Player;
import hu.unideb.fupn26.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/player/create")
    public void record(@RequestBody PlayerCreateRequestDto requestDto) {
        try {
            LocalDateTime birthDate = LocalDateTime.parse(requestDto.getBirthOfDate(), formatter);
            playerService.recordPlayer(new Player(
                    birthDate,
                    requestDto.getFirstName(),
                    requestDto.getLastName(),
                    requestDto.getHeight(),
                    requestDto.getWeight()
            ));
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Date format! Please use the following pattern: yyyy-MM-dd HH:mm:ss");
        } catch (InvalidPlayerArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/player/get/all")
    public Collection<PlayerDto> listPlayers(){
        return playerService.getAllPlayer()
                .stream()
                .map(model -> PlayerDto.builder()
                        .id(model.getId())
                        .birthOfDate(model.getBirthOfDate().format(formatter))
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .height(model.getHeight())
                        .weight(model.getWeight())
                .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/player/update")
    public void update(@RequestBody PlayerDto playerDto) {
        try {
            LocalDateTime birthDate = LocalDateTime.parse(playerDto.getBirthOfDate(), formatter);
            playerService.updatePlayer(new Player(
                    playerDto.getId(),
                    birthDate,
                    playerDto.getFirstName(),
                    playerDto.getLastName(),
                    playerDto.getHeight(),
                    playerDto.getWeight()
            ));
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Date format! Please use the following pattern: yyyy-MM-dd HH:mm:ss");
        } catch (InvalidPlayerArgumentException | UnknownPlayerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/player/delete")
    public void delete(@RequestParam("Player id") int id) {
        try {
            playerService.deletePlayer(new Player(id));

        } catch (UnknownPlayerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
