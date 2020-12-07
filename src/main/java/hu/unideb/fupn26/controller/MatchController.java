package hu.unideb.fupn26.controller;

import hu.unideb.fupn26.controller.dto.MatchMinimalRequestDto;
import hu.unideb.fupn26.controller.dto.MatchResponseDto;
import hu.unideb.fupn26.controller.dto.MatchFullRequestDto;
import hu.unideb.fupn26.exception.InvalidMatchArgumentException;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;
import hu.unideb.fupn26.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/match/create/minimal")
    public void recordMinimal(@RequestBody MatchMinimalRequestDto requestDto) {
        try {
            matchService.recordMatch(new Match(
                    requestDto.getSeason(),
                    requestDto.getRound(),
                    requestDto.getTeam1(),
                    requestDto.getTeam2(),
                    requestDto.getTeam1Location(),
                    requestDto.getTeam2Location(),
                    requestDto.getTeam1Score(),
                    requestDto.getTeam2Score()
            ));
        } catch (UnknownTeamException | InvalidMatchArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/match/create/full")
    public void recordFull(@RequestBody MatchFullRequestDto requestDto) {
        try {
            matchService.recordMatch(convertDtoToModel(requestDto));
        } catch (UnknownTeamException | InvalidMatchArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (DateTimeParseException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Date format! Please use the following pattern: yyyy-MM-dd HH:mm:ss");
        }
    }

    @GetMapping("/match/get/all")
    public Collection<MatchResponseDto> listMatches() {
        return matchService.getAllMatch()
                .stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }

    @GetMapping("/match/get/all/team")
    public Collection<MatchResponseDto> listMatchesByTeam(@RequestParam String teamName) {
        return matchService.getAllMatchByTeam(teamName)
                .stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/match/delete")
    public void delete(@RequestBody MatchFullRequestDto requestDto) {
        try {
            matchService.deleteMatch(convertDtoToModel(requestDto));
        } catch (UnknownMatchException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private Match convertDtoToModel(MatchFullRequestDto requestDto) {
        return Match.builder()
                .season(requestDto.getSeason())
                .round(requestDto.getRound())
                .team1(requestDto.getTeam1())
                .team2(requestDto.getTeam2())
                .team1Location(requestDto.getTeam1Location())
                .team2Location(requestDto.getTeam2Location())
                .team1Score(requestDto.getTeam1Score())
                .team2Score(requestDto.getTeam2Score())
                .startDate(LocalDateTime.parse(requestDto.getStartDate(), formatter))
                .venue(requestDto.getVenue())
                .attendants(requestDto.getAttendants())
                .margin(requestDto.getMargin())
                .homeQ1Goals(requestDto.getHomeQ1Goals())
                .homeQ2Goals(requestDto.getHomeQ2Goals())
                .homeQ3Goals(requestDto.getHomeQ3Goals())
                .homeQ4Goals(requestDto.getHomeQ4Goals())
                .homeExtraTimeGoals(requestDto.getHomeExtraTimeGoals())
                .homeQ1Behinds(requestDto.getHomeQ1Behinds())
                .homeQ2Behinds(requestDto.getHomeQ2Behinds())
                .homeQ3Behinds(requestDto.getHomeQ3Behinds())
                .homeQ4Behinds(requestDto.getHomeQ4Behinds())
                .homeExtraTimeBehinds(requestDto.getHomeExtraTimeBehinds())
                .awayQ1Goals(requestDto.getAwayQ1Goals())
                .awayQ2Goals(requestDto.getAwayQ2Goals())
                .awayQ3Goals(requestDto.getAwayQ3Goals())
                .awayQ4Goals(requestDto.getAwayQ4Goals())
                .awayExtraTimeGoals(requestDto.getAwayExtraTimeGoals())
                .awayQ1Behinds(requestDto.getAwayQ1Behinds())
                .awayQ2Behinds(requestDto.getAwayQ2Behinds())
                .awayQ3Behinds(requestDto.getAwayQ3Behinds())
                .awayQ4Behinds(requestDto.getAwayQ4Behinds())
                .awayExtraTimeBehinds(requestDto.getAwayExtraTimeBehinds())
                .build();
    }

    private MatchResponseDto convertModelToDto(Match match) {
        return MatchResponseDto.builder()
                .id(match.getId())
                .season(match.getSeason())
                .round(match.getRound())
                .team1(match.getTeam1())
                .team2(match.getTeam2())
                .team1Location(match.getTeam1Location())
                .team2Location(match.getTeam2Location())
                .team1Score(match.getTeam1Score())
                .team2Score(match.getTeam2Score())
                .startDate(match.getStartDate().format(formatter))
                .venue(match.getVenue())
                .attendants(match.getAttendants())
                .margin(match.getMargin())
                .homeQ1Goals(match.getHomeQ1Goals())
                .homeQ2Goals(match.getHomeQ2Goals())
                .homeQ3Goals(match.getHomeQ3Goals())
                .homeQ4Goals(match.getHomeQ4Goals())
                .homeExtraTimeGoals(match.getHomeExtraTimeGoals())
                .homeQ1Behinds(match.getHomeQ1Behinds())
                .homeQ2Behinds(match.getHomeQ2Behinds())
                .homeQ3Behinds(match.getHomeQ3Behinds())
                .homeQ4Behinds(match.getHomeQ4Behinds())
                .homeExtraTimeBehinds(match.getHomeExtraTimeBehinds())
                .awayQ1Goals(match.getAwayQ1Goals())
                .awayQ2Goals(match.getAwayQ2Goals())
                .awayQ3Goals(match.getAwayQ3Goals())
                .awayQ4Goals(match.getAwayQ4Goals())
                .awayExtraTimeGoals(match.getAwayExtraTimeGoals())
                .awayQ1Behinds(match.getAwayQ1Behinds())
                .awayQ2Behinds(match.getAwayQ2Behinds())
                .awayQ3Behinds(match.getAwayQ3Behinds())
                .awayQ4Behinds(match.getAwayQ4Behinds())
                .awayExtraTimeBehinds(match.getAwayExtraTimeBehinds())
                .build();
    }
}
