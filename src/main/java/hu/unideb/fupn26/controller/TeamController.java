package hu.unideb.fupn26.controller;

import hu.unideb.fupn26.controller.dto.TeamDto;
import hu.unideb.fupn26.exception.InvalidTeamNameException;
import hu.unideb.fupn26.exception.TeamAlreadyExistsException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Team;
import hu.unideb.fupn26.service.TeamService;
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
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/team/create")
    public void record(@RequestParam(name="Team name") String teamName) {
        try {
            teamService.recordTeam(new Team(teamName.trim()));
        } catch (TeamAlreadyExistsException | InvalidTeamNameException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/team/get/all")
    public Collection<TeamDto> listTeams(){
        return teamService.getAllTeam()
                .stream()
                .map(model -> new TeamDto(model.getId(), model.getTeamName()))
                .collect(Collectors.toList());
    }

    @PostMapping("/team/update")
    public void update(@RequestParam("Team id") int id, @RequestParam("New team name") String newTeamName) {
        try {
            teamService.updateTeam(new Team(id, newTeamName.trim()));
        } catch (UnknownTeamException | InvalidTeamNameException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/team/delete/id")
    public void deleteById(@RequestParam("Team id") int id) {
        try {
            teamService.deleteTeam(new Team(id));
        } catch (UnknownTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/team/delete/name")
    public void deleteByName(@RequestParam("Team name") String teamName) {
        try {
            teamService.deleteTeam(new Team(teamName.trim()));
        } catch (UnknownTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
