package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.MatchEntity;
import hu.unideb.fupn26.dao.entity.TeamEntity;
import hu.unideb.fupn26.dao.repository.MatchRepository;
import hu.unideb.fupn26.dao.repository.TeamRepository;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.model.Match;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchDaoImpl implements MatchDao {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @Override
    public void createMatch(Match match) {
        MatchEntity matchEntity;

        matchEntity = MatchEntity.builder()
                .id(String.format("%d_%s_%d_%d", match.getSeason(), match.getRound(),
                        queryTeamByName(match.getTeam1()).getId(), queryTeamByName(match.getTeam2()).getId()))
                .season(match.getSeason())
                .round(match.getRound())
                .team1(queryTeamByName(match.getTeam1()))
                .team2(queryTeamByName(match.getTeam2()))
                .team1Location(match.getTeam1Location())
                .team2Location(match.getTeam2Location())
                .startDate(match.getStartDate())
                .venue(match.getVenue())
                .attendants(match.getAttendants())
                .margin(match.getMargin())
                .winnerScore(match.getWinnerScore())
                .winnerTeam(queryTeamByName(match.getWinnerTeam()).getId())
                .winnerLocation(match.getWinnerLocation())
                .loserScore(match.getLoserScore())
                .loserTeam(queryTeamByName(match.getLoserTeam()).getId())
                .loserLocation(match.getLoserLocation())
                .homeTeam(queryTeamByName(match.getHomeTeam()).getId())
                .homeScore(match.getHomeScore())
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
                .awayTeam(queryTeamByName(match.getAwayTeam()).getId())
                .awayScore(match.getAwayScore())
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
                .target(match.getTeam1().equals(match.getWinnerTeam()) ? 1 : 0)
                .build();

        log.info("MatchEntity: {}", matchEntity);
        try {
            matchRepository.save(matchEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public Collection<Match> readAll() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .map(entity -> Match.builder()
                        .season(entity.getSeason())
                        .round(entity.getRound())
                        .team1(entity.getTeam1().getName())
                        .team2(entity.getTeam2().getName())
                        .team1Location(entity.getTeam1Location())
                        .team2Location(entity.getTeam2Location())
                        .startDate(entity.getStartDate())
                        .venue(entity.getVenue())
                        .attendants(entity.getAttendants())
                        .margin(entity.getMargin())
                        .winnerScore(entity.getWinnerScore())
                        .winnerTeam(queryTeamNameById(entity.getWinnerTeam()))
                        .winnerLocation(entity.getWinnerLocation())
                        .loserScore(entity.getLoserScore())
                        .loserTeam(queryTeamNameById(entity.getLoserTeam()))
                        .loserLocation(entity.getLoserLocation())
                        .homeTeam(queryTeamNameById(entity.getHomeTeam()))
                        .homeScore(entity.getHomeScore())
                        .homeQ1Goals(entity.getHomeQ1Goals())
                        .homeQ2Goals(entity.getHomeQ2Goals())
                        .homeQ3Goals(entity.getHomeQ3Goals())
                        .homeQ4Goals(entity.getHomeQ4Goals())
                        .homeExtraTimeGoals(entity.getHomeExtraTimeGoals())
                        .homeQ1Behinds(entity.getHomeQ1Behinds())
                        .homeQ2Behinds(entity.getHomeQ2Behinds())
                        .homeQ3Behinds(entity.getHomeQ3Behinds())
                        .homeQ4Behinds(entity.getHomeQ4Behinds())
                        .homeExtraTimeBehinds(entity.getHomeExtraTimeBehinds())
                        .awayTeam(queryTeamNameById(entity.getAwayTeam()))
                        .awayScore(entity.getAwayScore())
                        .awayQ1Goals(entity.getAwayQ1Goals())
                        .awayQ2Goals(entity.getAwayQ2Goals())
                        .awayQ3Goals(entity.getAwayQ3Goals())
                        .awayQ4Goals(entity.getAwayQ4Goals())
                        .awayExtraTimeGoals(entity.getAwayExtraTimeGoals())
                        .awayQ1Behinds(entity.getAwayQ1Behinds())
                        .awayQ2Behinds(entity.getAwayQ2Behinds())
                        .awayQ3Behinds(entity.getAwayQ3Behinds())
                        .awayQ4Behinds(entity.getAwayQ4Behinds())
                        .awayExtraTimeBehinds(entity.getAwayExtraTimeBehinds())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public void deleteMatch(Match match) throws UnknownMatchException {
        String id = String.format("%d_%s_%d_%d", match.getSeason(), match.getRound(),
                queryTeamByName(match.getTeam1()).getId(), queryTeamByName(match.getTeam2()).getId());

        if (!matchRepository.findById(id).isPresent()) {
            throw new UnknownMatchException(String.format("Match not found: %s", match), match);
        }
        matchRepository.deleteById(id);
    }

    private String queryTeamNameById(int teamId) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(teamId);

        if (!teamEntity.isPresent()) {
            log.warn("Team not found by id {}", teamId);
            return null;
        }

        return teamEntity.get().getName();
    }

    private TeamEntity queryTeamByName(String team) {
        //TODO what if team is not found?
        Optional<TeamEntity> teamEntity = teamRepository.findByName(team).stream()
                .filter(entity -> entity.getName().equals(team))
                .findFirst();

        log.trace("Team Entity: {}", teamEntity);
        return teamEntity.get();
    }

}
