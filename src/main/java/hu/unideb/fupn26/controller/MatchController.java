package hu.unideb.fupn26.controller;

import hu.unideb.fupn26.controller.dto.MatchDto;
import hu.unideb.fupn26.controller.dto.MatchRequestDto;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;
import hu.unideb.fupn26.service.MatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MatchController {

    private final MatchService matchService;

    @PostMapping("/match/create")
    public void record(@RequestBody MatchRequestDto requestDto) {
        try {
            matchService.recordMatch(convertDtoToModel(requestDto));
        } catch (UnknownTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/match/get/all")
    public Collection<MatchDto> listMatches() {
        return matchService.getAllMatch()
                .stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }

    @GetMapping("/match/get/all/team")
    public Collection<MatchDto> listMatchesByTeam(@RequestParam String teamName) {
        return matchService.getAllMatchByTeam(teamName)
                .stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/match/delete")
    public void delete(@RequestBody MatchRequestDto requestDto) {
        try {
            matchService.deleteMatch(convertDtoToModel(requestDto));
        } catch (UnknownMatchException | UnknownTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private Match convertDtoToModel(MatchRequestDto requestDto) {
        return new Match(
                requestDto.getSeason(),
                requestDto.getRound(),
                requestDto.getTeam1(),
                requestDto.getTeam2(),
                requestDto.getTeam1Location(),
                requestDto.getTeam2Location(),
                Timestamp.valueOf(LocalDateTime.of(requestDto.getYearOfStart(),
                        requestDto.getMonthOfStart(),
                        requestDto.getDayOfStart(),
                        requestDto.getHourOfStart(),
                        requestDto.getMinuteOfStart(),
                        requestDto.getSecondOfStart())),
                requestDto.getVenue(),
                requestDto.getAttendants(),
                requestDto.getMargin(),
                requestDto.getWinnerTeam(),
                requestDto.getWinnerScore(),
                requestDto.getWinnerLocation(),
                requestDto.getLoserTeam(),
                requestDto.getLoserScore(),
                requestDto.getLoserLocation(),
                requestDto.getHomeTeam(),
                requestDto.getHomeScore(),
                requestDto.getHomeQ1Goals(),
                requestDto.getHomeQ2Goals(),
                requestDto.getHomeQ3Goals(),
                requestDto.getHomeQ4Goals(),
                requestDto.getHomeExtraTimeGoals(),
                requestDto.getHomeQ1Behinds(),
                requestDto.getHomeQ2Behinds(),
                requestDto.getHomeQ3Behinds(),
                requestDto.getHomeQ4Behinds(),
                requestDto.getHomeExtraTimeBehinds(),
                requestDto.getAwayTeam(),
                requestDto.getAwayScore(),
                requestDto.getAwayQ1Goals(),
                requestDto.getAwayQ2Goals(),
                requestDto.getAwayQ3Goals(),
                requestDto.getAwayQ4Goals(),
                requestDto.getAwayExtraTimeGoals(),
                requestDto.getAwayQ1Behinds(),
                requestDto.getAwayQ2Behinds(),
                requestDto.getAwayQ3Behinds(),
                requestDto.getAwayQ4Behinds(),
                requestDto.getAwayExtraTimeBehinds()
        );
    }

    private MatchDto convertModelToDto(Match match) {
        LocalDateTime matchDate = match.getStartDate().toLocalDateTime();

        return new MatchDto(
                match.getSeason(),
                match.getRound(),
                match.getTeam1(),
                match.getTeam2(),
                match.getTeam1Location(),
                match.getTeam2Location(),
                matchDate.getYear(),
                matchDate.getMonthValue(),
                matchDate.getDayOfMonth(),
                matchDate.getHour(),
                matchDate.getMinute(),
                matchDate.getSecond(),
                match.getVenue(),
                match.getAttendants(),
                match.getMargin(),
                match.getWinnerTeam(),
                match.getWinnerScore(),
                match.getWinnerLocation(),
                match.getLoserTeam(),
                match.getLoserScore(),
                match.getLoserLocation(),
                match.getHomeScore(),
                match.getHomeQ1Goals(),
                match.getHomeQ2Goals(),
                match.getHomeQ3Goals(),
                match.getHomeQ4Goals(),
                match.getHomeExtraTimeGoals(),
                match.getHomeQ1Behinds(),
                match.getHomeQ2Behinds(),
                match.getHomeQ3Behinds(),
                match.getHomeQ4Behinds(),
                match.getHomeExtraTimeBehinds(),
                match.getAwayScore(),
                match.getAwayQ1Goals(),
                match.getAwayQ2Goals(),
                match.getAwayQ3Goals(),
                match.getAwayQ4Goals(),
                match.getAwayExtraTimeGoals(),
                match.getAwayQ1Behinds(),
                match.getAwayQ2Behinds(),
                match.getAwayQ3Behinds(),
                match.getAwayQ4Behinds(),
                match.getAwayExtraTimeBehinds()
        );
    }
}
