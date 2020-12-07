package hu.unideb.fupn26.dao.repository;

import hu.unideb.fupn26.dao.entity.MatchStatEntity;
import hu.unideb.fupn26.dao.entity.MatchStatId;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface MatchStatRepository extends CrudRepository<MatchStatEntity, MatchStatId> {

    Collection<MatchStatEntity> findById_Match_Id(String id);
}
