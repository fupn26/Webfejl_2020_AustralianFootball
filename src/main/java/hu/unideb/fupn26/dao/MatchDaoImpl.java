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
                .map(entity -> new Match(
                        entity.getSeason(),
                        entity.getRound(),
                        entity.getTeam1().getName(),
                        entity.getTeam2().getName(),
                        entity.getTeam1Location(),
                        entity.getTeam2Location(),
                        entity.getStartDate(),
                        entity.getVenue(),
                        entity.getAttendants(),
                        entity.getMargin(),
                        queryTeamNameById(entity.getWinnerTeam()),
                        entity.getWinnerScore(),
                        entity.getWinnerLocation(),
                        queryTeamNameById(entity.getLoserTeam()),
                        entity.getLoserScore(),
                        entity.getLoserLocation(),
                        queryTeamNameById(entity.getHomeTeam()),
                        entity.getHomeScore(),
                        entity.getHomeQ1Goals(),
                        entity.getHomeQ2Goals(),
                        entity.getHomeQ3Goals(),
                        entity.getHomeQ4Goals(),
                        entity.getHomeExtraTimeGoals(),
                        entity.getHomeQ1Behinds(),
                        entity.getHomeQ2Behinds(),
                        entity.getHomeQ3Behinds(),
                        entity.getHomeQ4Behinds(),
                        entity.getHomeExtraTimeBehinds(),
                        queryTeamNameById(entity.getAwayTeam()),
                        entity.getAwayScore(),
                        entity.getAwayQ1Goals(),
                        entity.getAwayQ2Goals(),
                        entity.getAwayQ3Goals(),
                        entity.getAwayQ4Goals(),
                        entity.getAwayExtraTimeGoals(),
                        entity.getAwayQ1Behinds(),
                        entity.getAwayQ2Behinds(),
                        entity.getAwayQ3Behinds(),
                        entity.getAwayQ4Behinds(),
                        entity.getAwayExtraTimeBehinds()
                        )
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
        Optional<TeamEntity> teamEntity = teamRepository.findByName(team);

        log.trace("Team Entity: {}", teamEntity);
        return teamEntity.get();
    }

}
