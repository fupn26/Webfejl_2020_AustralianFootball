package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.MatchEntity;
import hu.unideb.fupn26.dao.entity.TeamEntity;
import hu.unideb.fupn26.dao.repository.MatchRepository;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.TeamRepository;
import hu.unideb.fupn26.exception.MatchAlreadyExistsException;
import hu.unideb.fupn26.exception.MatchSqlIntegrityException;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.Match;
import hu.unideb.fupn26.model.MatchLocation;
import hu.unideb.fupn26.model.MatchRound;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
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
    private final MatchStatRepository matchStatRepository;

    @Override
    public void createMatch(Match match) throws UnknownTeamException, MatchAlreadyExistsException {
        MatchEntity matchEntity;

        String winnerTeam = match.getTeam1Score() > match.getTeam2Score() ?
                match.getTeam1() :
                match.getTeam2();

        String id = String.format("%d_%s_%d_%d", match.getSeason(), match.getRound(),
                queryTeamByName(match.getTeam1()).getId(), queryTeamByName(match.getTeam2()).getId());

        if (matchRepository.findById(id).isPresent()) {
            throw new MatchAlreadyExistsException(String.format("Match already exists: %s", match));
        }

        matchEntity = MatchEntity.builder()
                .id(id)
                .season(match.getSeason())
                .round(match.getRound().name())
                .team1(queryTeamByName(match.getTeam1()))
                .team2(queryTeamByName(match.getTeam2()))
                .team1Location(match.getTeam1Location().name().toLowerCase())
                .team2Location(match.getTeam2Location().name().toLowerCase())
                .startDate(match.getStartDate() != null ? Timestamp.valueOf(match.getStartDate()) : null)
                .venue(match.getVenue())
                .attendants(match.getAttendants())
                .margin(match.getMargin())
                .winnerScore(Integer.max(match.getTeam1Score(), match.getTeam2Score()))
                .winnerTeam(queryTeamByName(winnerTeam).getId())
                .winnerLocation(winnerTeam.equals(match.getTeam1()) ?
                        match.getTeam1Location().name().toLowerCase() :
                        match.getTeam2Location().name().toLowerCase()
                )
                .loserScore(Integer.min(match.getTeam1Score(), match.getTeam2Score()))
                .loserTeam(queryTeamByName(winnerTeam.equals(match.getTeam1()) ?
                            match.getTeam2() :
                            match.getTeam1()
                        ).getId()
                )
                .loserLocation(winnerTeam.equals(match.getTeam1()) ?
                        match.getTeam2Location().name().toLowerCase() :
                        match.getTeam1Location().name().toLowerCase()
                )
                .homeTeam(queryTeamByName(match.getTeam1Location() == MatchLocation.H ?
                            match.getTeam1() :
                            match.getTeam2()
                        ).getId()
                )
                .homeScore(match.getTeam1Location() == MatchLocation.H ?
                        match.getTeam1Score() :
                        match.getTeam2Score()
                )
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
                .awayTeam(queryTeamByName(match.getTeam1Location() == MatchLocation.A ?
                            match.getTeam1() :
                            match.getTeam2()
                        ).getId()
                )
                .awayScore(match.getTeam1Location() == MatchLocation.A ?
                        match.getTeam1Score() :
                        match.getTeam2Score()
                )
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
                .target(match.getTeam1().equals(winnerTeam) ? 1 : 0)
                .build();

        log.info("MatchEntity: {}", matchEntity);
        try {
            matchRepository.save(matchEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Override
    public void updateMatch(Match match) throws UnknownMatchException {
        Optional<MatchEntity> matchEntity = matchRepository.findById(match.getId());
        if (matchEntity.isEmpty())
            throw new UnknownMatchException(String.format("Match not found: %s", match));

        MatchEntity newMatchEntity = matchEntity.get();

        TeamEntity winnerTeam = match.getTeam1Score() > match.getTeam2Score() ?
                newMatchEntity.getTeam1() :
                newMatchEntity.getTeam2();

        newMatchEntity.setTeam1Location(match.getTeam1Location().name().toLowerCase());
        newMatchEntity.setTeam2Location(match.getTeam2Location().name().toLowerCase());
        newMatchEntity.setStartDate(match.getStartDate() != null ?
                Timestamp.valueOf(match.getStartDate()) :
                null
        );
        newMatchEntity.setVenue(match.getVenue());
        newMatchEntity.setAttendants(match.getAttendants());
        newMatchEntity.setMargin(match.getMargin());
        newMatchEntity.setWinnerScore(Integer.max(match.getTeam1Score(), match.getTeam2Score()));
        newMatchEntity.setWinnerTeam(winnerTeam.getId());
        newMatchEntity.setWinnerLocation((winnerTeam.equals(newMatchEntity.getTeam1()) ?
                match.getTeam1Location().name().toLowerCase() :
                match.getTeam2Location().name().toLowerCase()
        ));
        newMatchEntity.setLoserScore(Integer.min(match.getTeam1Score(), match.getTeam2Score()));
        newMatchEntity.setLoserTeam((winnerTeam.equals(newMatchEntity.getTeam1()) ?
                newMatchEntity.getTeam2() :
                newMatchEntity.getTeam1()
                ).getId()
        );
        newMatchEntity.setLoserLocation(winnerTeam.equals(newMatchEntity.getTeam1()) ?
                match.getTeam2Location().name().toLowerCase() :
                match.getTeam1Location().name().toLowerCase()
        );
        newMatchEntity.setHomeTeam((match.getTeam1Location() == MatchLocation.H ?
                        newMatchEntity.getTeam1() :
                        newMatchEntity.getTeam2()
                ).getId()
        );
        newMatchEntity.setHomeScore(match.getTeam1Location() == MatchLocation.H ?
                match.getTeam1Score() :
                match.getTeam2Score()
        );
        newMatchEntity.setHomeQ1Goals(match.getHomeQ1Goals());
        newMatchEntity.setHomeQ2Goals(match.getHomeQ2Goals());
        newMatchEntity.setHomeQ3Goals(match.getHomeQ3Goals());
        newMatchEntity.setHomeQ4Goals(match.getHomeQ4Goals());
        newMatchEntity.setHomeExtraTimeGoals(match.getHomeExtraTimeGoals());
        newMatchEntity.setHomeQ1Behinds(match.getHomeQ1Behinds());
        newMatchEntity.setHomeQ2Behinds(match.getHomeQ2Behinds());
        newMatchEntity.setHomeQ3Behinds(match.getHomeQ3Behinds());
        newMatchEntity.setHomeQ4Behinds(match.getHomeQ4Behinds());
        newMatchEntity.setHomeExtraTimeBehinds(match.getHomeExtraTimeBehinds());
        newMatchEntity.setAwayTeam((match.getTeam1Location() == MatchLocation.A ?
                newMatchEntity.getTeam1() :
                newMatchEntity.getTeam2()
            ).getId()
        );
        newMatchEntity.setAwayScore(match.getTeam1Location() == MatchLocation.A ?
                    match.getTeam1Score() :
                    match.getTeam2Score()
        );
        newMatchEntity.setAwayQ1Goals(match.getAwayQ1Goals());
        newMatchEntity.setAwayQ2Goals(match.getAwayQ2Goals());
        newMatchEntity.setAwayQ3Goals(match.getAwayQ3Goals());
        newMatchEntity.setAwayQ4Goals(match.getAwayQ4Goals());
        newMatchEntity.setAwayExtraTimeGoals(match.getAwayExtraTimeGoals());
        newMatchEntity.setAwayQ1Behinds(match.getAwayQ1Behinds());
        newMatchEntity.setAwayQ2Behinds(match.getAwayQ2Behinds());
        newMatchEntity.setAwayQ3Behinds(match.getAwayQ3Behinds());
        newMatchEntity.setAwayQ4Behinds(match.getAwayQ4Behinds());
        newMatchEntity.setAwayExtraTimeBehinds(match.getAwayExtraTimeBehinds());

        log.info("MatchEntity: {}", newMatchEntity);
        try {
            matchRepository.save(newMatchEntity);
        } catch (Exception e) {
            log.error("Can't update match: {}", e.getMessage());
        }

    }

    @Override
    public Collection<Match> readAll() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .map(entity -> new Match(
                        entity.getId(),
                        entity.getSeason(),
                        MatchRound.valueOf(entity.getRound()),
                        entity.getTeam1().getName(),
                        entity.getTeam2().getName(),
                        MatchLocation.valueOf(entity.getTeam1Location().toUpperCase()),
                        MatchLocation.valueOf(entity.getTeam2Location().toUpperCase()),
                        entity.getWinnerTeam() == entity.getTeam1().getId() ?
                            entity.getWinnerScore() :
                            entity.getLoserScore(),
                        entity.getWinnerTeam() == entity.getTeam2().getId() ?
                                entity.getWinnerScore() :
                                entity.getLoserScore(),
                        entity.getStartDate() != null ? entity.getStartDate().toLocalDateTime() : null,
                        entity.getVenue(),
                        entity.getAttendants(),
                        entity.getMargin(),
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
    public void deleteMatch(Match match) throws UnknownMatchException, MatchSqlIntegrityException {

        if (matchRepository.findById(match.getId()).isEmpty()) {
            throw new UnknownMatchException(String.format("Match not found: %s", match), match);
        }

        if (!matchStatRepository.findById_Match_Id(match.getId()).isEmpty()) {
            throw new MatchSqlIntegrityException("Match can't be deleted because it is referenced in MatchStat table.");
        }

        try {
            matchRepository.deleteById(match.getId());
        } catch (Exception e) {
            log.error("Can't delete match: {}", e.getMessage());
        }
    }

    private String queryTeamNameById(int teamId) {
        Optional<TeamEntity> teamEntity = teamRepository.findById(teamId);

        if (!teamEntity.isPresent()) {
            log.warn("Team not found by id {}", teamId);
            return "Unknown";
        }

        return teamEntity.get().getName();
    }

    private TeamEntity queryTeamByName(String team) throws UnknownTeamException {
        Optional<TeamEntity> teamEntity = teamRepository.findByName(team);

        if (teamEntity.isEmpty())
            throw new UnknownTeamException(String.format("Team not found: %s", team));

        log.trace("Team Entity: {}", teamEntity);
        return teamEntity.get();
    }

}
