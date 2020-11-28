package hu.unideb.fupn26.dao.repository;

import hu.unideb.fupn26.dao.entity.MatchStatEntity;
import hu.unideb.fupn26.dao.entity.MatchStatId;
import org.springframework.data.repository.CrudRepository;

public interface MatchStatRepository extends CrudRepository<MatchStatEntity, MatchStatId> {
}
