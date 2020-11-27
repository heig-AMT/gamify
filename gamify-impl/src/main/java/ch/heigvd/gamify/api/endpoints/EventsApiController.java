package ch.heigvd.gamify.api.endpoints;

import ch.heigvd.gamify.api.EventsApi;
import ch.heigvd.gamify.api.filters.UserFilter;
import ch.heigvd.gamify.api.model.Event;
import ch.heigvd.gamify.entities.EventEntity;
import ch.heigvd.gamify.entities.RegisteredAppEntity;
import ch.heigvd.gamify.repositories.EventRepository;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EventsApiController implements EventsApi {

  @Autowired
  EventRepository repository;

  @Autowired
  ServletRequest request;

  @Override
  public ResponseEntity<Void> addEvent(@Valid Event event) {
    var app = (RegisteredAppEntity) request.getAttribute(UserFilter.APP_KEY);
    var entity = this.repository.save(EventEntity.builder()
        .app(app)
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
