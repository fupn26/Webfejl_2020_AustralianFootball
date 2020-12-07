package hu.unideb.fupn26.dao.repository;

import hu.unideb.fupn26.dao.entity.MatchEntity;
import hu.unideb.fupn26.dao.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MatchRepository extends CrudRepository<MatchEntity, String> {

    Collection<MatchEntity> findByTeam1OrTeam2(TeamEntity entity1, TeamEntity entity2);
}
