package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {
}
