package hu.unideb.fupn26.service;

import hu.unideb.fupn26.dao.MatchStatDao;
import hu.unideb.fupn26.exception.UnknownMatchException;
import hu.unideb.fupn26.exception.UnknownPlayerException;
import hu.unideb.fupn26.exception.UnknownTeamException;
import hu.unideb.fupn26.model.MatchStat;
import hu.unideb.fupn26.model.Player;
import hu.unideb.fupn26.model.Team;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchStatServiceImpl implements MatchStatService{

    private final MatchStatDao matchStatDao;

    @Override
    public Collection<MatchStat> getAllMatchStat() {
        return matchStatDao.readAll();
    }

    @Override
    public Collection<MatchStat> getAllMatchStatByPlayer(Player player) {
        return matchStatDao.readAll().stream()
                .filter(matchStat -> matchStat.getPlayerFirstName().equals(player.getFirstName()) &&
                            matchStat.getPlayerLastName().equals(player.getLastName())
                )
                .collect(Collectors.toList());
    }

    @Override
    public Collection<MatchStat> getAllMatchStatByTeam(Team team) {
        return matchStatDao.readAll().stream()
                .filter(matchStat -> matchStat.getPlayerTeam().equals(team.getTeamName()) ||
                        matchStat.getOpponentTeam().equals(team.getTeamName())
                )
                .collect(Collectors.toList());
    }

    @Override
    public void recordMatchStat(MatchStat matchStat) throws UnknownMatchException, UnknownPlayerException, UnknownTeamException {
        matchStatDao.createMatchStat(matchStat);
    }

    @Override
    public void deleteMatchStat(MatchStat matchStat) throws UnknownPlayerException, UnknownMatchException {
        matchStatDao.deleteMatchStat(matchStat);
    }
}
