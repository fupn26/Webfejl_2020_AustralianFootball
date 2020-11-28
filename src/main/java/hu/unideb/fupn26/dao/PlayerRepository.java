package hu.unideb.fupn26.dao;

import hu.unideb.fupn26.dao.entity.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Integer> {
}
