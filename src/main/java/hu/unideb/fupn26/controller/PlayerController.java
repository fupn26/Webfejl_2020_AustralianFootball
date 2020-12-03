package hu.unideb.fupn26.controller;

import hu.unideb.fupn26.controller.dto.PlayerDto;
import hu.unideb.fupn26.controller.dto.PlayerRequestDto;
import hu.unideb.fupn26.exception.PlayerAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.model.Player;
import hu.unideb.fupn26.service.PlayerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping("/player/create")
    public void record(@RequestBody PlayerRequestDto requestDto) {
        try {
            playerService.recordPlayer(new Player(
                    requestDto.getYearOfBirth(),
                    requestDto.getMonthOfBirth(),
                    requestDto.getDayOfBirth(),
                    requestDto.getFirstName(),
                    requestDto.getLastName(),
                    requestDto.getHeight(),
                    requestDto.getWeight()
            ));
        } catch (PlayerAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/player/get/all")
    public Collection<PlayerDto> listPlayers(){
        return playerService.getAllPlayer()
                .stream()
                .map(model -> new PlayerDto(
                        model.getYearOfBirth(),
                        model.getMonthOfBirth(),
                        model.getDayOfBirth(),
                        model.getFirstName(),
                        model.getLastName()
                ))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/player/delete")
    public void delete(@RequestBody PlayerRequestDto requestDto) {
        try {
            playerService.deletePlayer(new Player(
                    requestDto.getYearOfBirth(),
                    requestDto.getMonthOfBirth(),
                    requestDto.getDayOfBirth(),
                    requestDto.getFirstName(),
                    requestDto.getLastName(),
                    requestDto.getHeight(),
                    requestDto.getWeight()
            ));
        } catch (UnknownPlayerException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
