package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.MatchEntity;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<MatchEntity, String> {
}
