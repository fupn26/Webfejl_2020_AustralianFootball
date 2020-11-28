package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.MatchStatEntity;
import hu.unideb.fupn26.model.MatchStat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class MatchStatDaoImpl implements MatchStatDao{

    private final MatchStatRepository matchStatRepository;

    @Override
    public void createMatchStat(MatchStat address) {
    }

    @Override
    public Collection<MatchStat> readAll() {
        return null;
    }

    @Override
    public void deleteMatchStat(MatchStat matchStat) {

    }
}
