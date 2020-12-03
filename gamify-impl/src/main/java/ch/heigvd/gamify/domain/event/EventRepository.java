package ch.heigvd.gamify.domain.event;

import ch.heigvd.gamify.domain.event.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, Long> {

}
