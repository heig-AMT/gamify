package ch.heigvd.gamify.repositories;

import ch.heigvd.gamify.entities.FruitEntity;
import org.springframework.data.repository.CrudRepository;

public interface FruitRepository extends CrudRepository<FruitEntity, Long> {

}
