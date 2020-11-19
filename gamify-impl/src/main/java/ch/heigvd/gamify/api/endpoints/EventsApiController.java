package ch.heigvd.gamify.api.endpoints;

import ch.heigvd.gamify.api.EventsApi;
import ch.heigvd.gamify.api.model.Event;
import ch.heigvd.gamify.entities.EventEntity;
import ch.heigvd.gamify.repositories.EventRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class EventsApiController implements EventsApi {

  private final EventRepository repository;

  public EventsApiController(EventRepository repository) {
    this.repository = repository;
  }

  @Override
  public ResponseEntity<Void> addEvent(@Valid Event event) {
    var entity = this.repository.save(EventEntity.builder()
        .timestamp(event.getTimestamp())
        .type(event.getType())
        .user(event.getUserId())
        .build()
    );
    var location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(entity.getId());
    return ResponseEntity.created(location.toUri()).build();
  }

  @Override
  public ResponseEntity<List<Event>> getEvents() {
    var items = StreamSupport.stream(repository.findAll().spliterator(), false)
        .sorted(Comparator.comparing(EventEntity::getId))
        .map(entity -> new Event()
            .timestamp(entity.getTimestamp())
            .type(entity.getType())
            .userId(entity.getUser())
        )
        .collect(Collectors.toList());
    return ResponseEntity.ok(items);
  }
}
