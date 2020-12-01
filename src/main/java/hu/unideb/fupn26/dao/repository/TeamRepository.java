package hu.unideb.fupn26.dao.repository;

import hu.unideb.fupn26.dao.entity.TeamEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<TeamEntity, Integer> {

    Optional<TeamEntity> findByName(String name);
}
