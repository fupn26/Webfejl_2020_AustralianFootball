package hu.unideb.fupn26.dao.repository;

import hu.unideb.fupn26.dao.entity.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Optional;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Integer> {

    Collection<PlayerEntity> findByFirstNameAndLastName(String firstName, String lastName);
}
