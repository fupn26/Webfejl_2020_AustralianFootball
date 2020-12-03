package hu.unideb.fupn26.controller;

import hu.unideb.fupn26.controller.dto.TeamDto;
import hu.unideb.fupn26.exception.TeamAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Team;
import hu.unideb.fupn26.service.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team/create")
    public void record(@RequestBody TeamDto requestDto) {
        try {
            teamService.recordTeam(new Team(requestDto.getTeamName()));
        } catch (TeamAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/team/get_all")
    public Collection<TeamDto> listAddresses(){
        return teamService.getAllTeam()
                .stream()
                .map(model -> new TeamDto(model.getTeamName()))
                .collect(Collectors.toList());
    }

    @PostMapping("/team/delete")
    public void delete(@RequestBody TeamDto requestDto) {
        try {
            teamService.deleteTeam(new Team(requestDto.getTeamName()));
        } catch (UnknownTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
