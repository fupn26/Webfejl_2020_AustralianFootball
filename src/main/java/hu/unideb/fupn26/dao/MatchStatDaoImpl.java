package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.*;
import hu.unideb.fupn26.dao.repository.MatchRepository;
import hu.unideb.fupn26.dao.repository.MatchStatRepository;
import hu.unideb.fupn26.dao.repository.TeamRepository;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.MatchStat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchStatDaoImpl implements MatchStatDao{

    private final MatchStatRepository matchStatRepository;
    private final MatchRepository matchRepository;
    private final TeamRepository teamRepository;

    @Override
    public void createMatchStat(MatchStat matchStat) throws UnknownMatchException {
        MatchStatEntity matchStatEntity;
        MatchEntity matchEntity = queryMatchByMatchStat(matchStat);
    }

    private MatchEntity queryMatchByMatchStat(MatchStat matchStat) throws UnknownMatchException {
        int playerTeamId;
        int opponentTeamId;
        String playerTeamVsOpponentTeamMatch;
        String opponentTeamVsPlayerTeamMatch;

        try {
            playerTeamId = queryTeamIdByName(matchStat.getPlayerTeam());
            opponentTeamId = queryTeamIdByName(matchStat.getOpponentTeam());
        } catch (UnknownTeamException e) {
            log.error("Team not found!");
            throw new UnknownMatchException(String.format("Match not found: %s", e.getMessage()));
        }

        playerTeamVsOpponentTeamMatch = String.format("%d_%s_%d_%d"
                ,matchStat.getSeason()
                ,matchStat.getRound()
                ,playerTeamId
                ,opponentTeamId
        );

        opponentTeamVsPlayerTeamMatch = String.format("%d_%s_%d_%d"
                ,matchStat.getSeason()
                ,matchStat.getRound()
                ,opponentTeamId
                ,playerTeamId
        );

        Optional<MatchEntity> matchEntity = StreamSupport.stream (matchRepository
                .findAllById(Arrays.asList(playerTeamVsOpponentTeamMatch, opponentTeamVsPlayerTeamMatch))
                .spliterator(), false)
                .filter(entity -> {
                    if (entity.getTeam1().getId() == playerTeamId
                            && entity.getTeam1Location().equals(matchStat.getLocation()))
                        return true;
                    else if (entity.getTeam2().getId() == playerTeamId
                            && entity.getTeam2Location().equals(matchStat.getLocation()))
                        return true;
                    else
                        return false;
                })
                .findFirst();

        if (!matchEntity.isPresent()) {
            log.error("Match not found");
            throw new UnknownMatchException("Match not found");
        }

        return matchEntity.get();
    }

    private int queryTeamIdByName(String team) throws UnknownTeamException {
        Optional<TeamEntity> teamEntity = teamRepository.findByName(team).stream()
                .filter(entity -> entity.getName().equals(team))
                .findFirst();

        if (!teamEntity.isPresent()) {
            throw new UnknownTeamException(String.format("Team not found: %s", team));
        } else {
            log.trace("Team Entity: {}", teamEntity);
            return teamEntity.get().getId();
        }
    }

    @Override
    public Collection<MatchStat> readAll() {
        return null;
    }

    @Override
    public void deleteMatchStat(MatchStat matchStat) {

    }
}
