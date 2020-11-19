package ch.heigvd.gamify.api.endpoints;

import ch.heigvd.gamify.api.EventsApi;
import ch.heigvd.gamify.api.model.Event;
import ch.heigvd.gamify.entities.EventEntity;
import ch.heigvd.gamify.repositories.EventRepository;
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
}
