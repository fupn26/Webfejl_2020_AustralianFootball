package hu.unideb.fupn26.controller;

import java.util.Collection;
import java.util.stream.Collectors;

import hu.unideb.fupn26.controller.dto.MatchStatDto;
import hu.unideb.fupn26.controller.dto.MatchStatMinimalDto;
import hu.unideb.fupn26.controller.dto.MatchStatUpdateRequestDto;
import hu.unideb.fupn26.exception.*;
import hu.unideb.fupn26.model.MatchStat;
import hu.unideb.fupn26.service.MatchStatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MatchStatController {

    private final MatchStatService matchStatService;

    @PostMapping("/match_stat/create/minimal")
    public void recordMinimal(@RequestBody MatchStatMinimalDto matchStatDto) {
        try {
            matchStatService.recordMatchStat(MatchStat.builder()
                    .matchId(matchStatDto.getMatchId())
                    .playerId(matchStatDto.getPlayerId())
                    .teamId(matchStatDto.getTeamId())
                    .goals(matchStatDto.getGoals())
                    .behinds(matchStatDto.getBehinds())
                    .build()
            );
        } catch (UnknownMatchException | UnknownPlayerException | InvalidMatchStatArgumentException |
                MatchStatAlreadyExistsException | UnknownTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/match_stat/create/full")
    public void recordFull(@RequestBody MatchStatDto matchStatDto) {
        try {
            matchStatService.recordMatchStat(new MatchStat(
                    matchStatDto.getMatchId(),
                    matchStatDto.getPlayerId(),
                    matchStatDto.getTeamId(),
                    matchStatDto.getGoals(),
                    matchStatDto.getBehinds(),
                    matchStatDto.getKicks(),
                    matchStatDto.getMarks(),
                    matchStatDto.getHandballs(),
                    matchStatDto.getDisposals(),
                    matchStatDto.getHitOuts(),
                    matchStatDto.getTackles(),
                    matchStatDto.getRebound50s(),
                    matchStatDto.getInside50s(),
                    matchStatDto.getClearances(),
                    matchStatDto.getClangers(),
                    matchStatDto.getFreeKicksFor(),
                    matchStatDto.getFreeKicksAgainst(),
                    matchStatDto.getBrownlowVotes(),
                    matchStatDto.getContestedPossessions(),
                    matchStatDto.getUncontestedPossessions(),
                    matchStatDto.getContestedMarks(),
                    matchStatDto.getMarksInside50(),
                    matchStatDto.getOnePercenters(),
                    matchStatDto.getBounces(),
                    matchStatDto.getGoalAssist(),
                    matchStatDto.getPercentageOfGamePlayed()
            ));
        } catch (InvalidMatchStatArgumentException | UnknownMatchException | MatchStatAlreadyExistsException |
                UnknownPlayerException | UnknownTeamException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/match_stat/update")
    public void updateMatchStat(@RequestParam(name="Match ID") String matchId,
                                @RequestParam(name="Team ID") int teamId,
                                @RequestBody MatchStatUpdateRequestDto matchStatDto) {
        try {
            matchStatService.updateMatchStat(new MatchStat(
                    matchId,
                    teamId,
                    matchStatDto.getTeamId(),
                    matchStatDto.getGoals(),
                    matchStatDto.getBehinds(),
                    matchStatDto.getKicks(),
                    matchStatDto.getMarks(),
                    matchStatDto.getHandballs(),
                    matchStatDto.getDisposals(),
                    matchStatDto.getHitOuts(),
                    matchStatDto.getTackles(),
                    matchStatDto.getRebound50s(),
                    matchStatDto.getInside50s(),
                    matchStatDto.getClearances(),
                    matchStatDto.getClangers(),
                    matchStatDto.getFreeKicksFor(),
                    matchStatDto.getFreeKicksAgainst(),
                    matchStatDto.getBrownlowVotes(),
                    matchStatDto.getContestedPossessions(),
                    matchStatDto.getUncontestedPossessions(),
                    matchStatDto.getContestedMarks(),
                    matchStatDto.getMarksInside50(),
                    matchStatDto.getOnePercenters(),
                    matchStatDto.getBounces(),
                    matchStatDto.getGoalAssist(),
                    matchStatDto.getPercentageOfGamePlayed()
            ));
        } catch (InvalidMatchStatArgumentException | UnknownPlayerException | UnknownTeamException |
                UnknownMatchException | UnknownMatchStatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/match_stat/read/all")
    public Collection<MatchStatDto> listMatches() {
        return matchStatService.getAllMatchStat().stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/match_stat/read/all/team")
    public Collection<MatchStatDto> listMatchesByTeam(@RequestParam(name="Team ID") int id) {
        return matchStatService.getAllMatchStatByTeam(id).stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/match_stat/read/all/player")
    public Collection<MatchStatDto> listMatchesByPlayer(@RequestParam(name="Player ID") int id) {
        return matchStatService.getAllMatchStatByPlayer(id).stream()
                .map(this::convertModelToDto)
                .collect(Collectors.toList());
    }

    @DeleteMapping("/match_stat/delete")
    public void deleteMatchStat(@RequestParam(name="Match ID") String matchId, @RequestParam(name="Player ID") int playerId) {
        try {
            matchStatService.deleteMatchStat(new MatchStat(matchId, playerId));
        } catch (UnknownPlayerException | UnknownMatchException | InvalidMatchStatArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    private MatchStatDto convertModelToDto(MatchStat model) {
        return MatchStatDto.builder()
                .playerId(model.getPlayerId())
                .teamId(model.getTeamId())
                .goals(model.getGoals())
                .behinds(model.getBehinds())
                .kicks(model.getKicks())
                .marks(model.getMarks())
                .handballs(model.getHandballs())
                .disposals(model.getDisposals())
                .hitOuts(model.getHitOuts())
                .tackles(model.getTackles())
                .rebound50s(model.getRebound50s())
                .inside50s(model.getInside50s())
                .clearances(model.getClearances())
                .clangers(model.getClangers())
                .freeKicksFor(model.getFreeKicksFor())
                .freeKicksAgainst(model.getFreeKicksAgainst())
                .brownlowVotes(model.getBrownlowVotes())
                .contestedPossessions(model.getContestedPossessions())
                .uncontestedPossessions(model.getUncontestedPossessions())
                .contestedMarks(model.getContestedMarks())
                .marksInside50(model.getMarksInside50())
                .onePercenters(model.getOnePercenters())
                .bounces(model.getBounces())
                .goalAssist(model.getGoalAssist())
                .percentageOfGamePlayed(model.getPercentageOfGamePlayed())
                .matchId(model.getMatchId())
                .build();
    }
}
