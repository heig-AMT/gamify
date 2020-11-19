package ch.heigvd.gamify.repositories;

import ch.heigvd.gamify.entities.EventEntity;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<EventEntity, Long> {

}
