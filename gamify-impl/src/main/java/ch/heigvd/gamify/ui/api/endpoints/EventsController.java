package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.EventsApi;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.endUser.EndUser;
import ch.heigvd.gamify.domain.endUser.EndUserRepository;
import ch.heigvd.gamify.domain.event.Event;
import ch.heigvd.gamify.domain.event.EventRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EventsController implements EventsApi {

  @Autowired
  EventRepository repository;

  @Autowired
  EndUserRepository userRepository;

  @Autowired
  ServletRequest request;

  @Override
  public ResponseEntity<Void> addEvent(@Valid ch.heigvd.gamify.api.model.Event event) {
    var app = (App) request.getAttribute(ApiKeyFilter.APP_KEY);
    var entity = this.repository.save(Event.builder()
        .app(app)
        .timestamp(event.getTimestamp())
        .type(event.getType())
        .user(event.getUserId())
        .build()
    );
    var location = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(entity.getId());
    addEventPoints(app, 0);
    return ResponseEntity.created(location.toUri()).build();
  }


  private void addEventPoints(App app, int points) {
    var userId = userRepository.findByUserId(app.getName());
    if (userId.isPresent()) {
      userId.get().addPoints(points);
    } else {
        userRepository.save(
            EndUser.builder()
                .userId(app.getName())
                .points(points)
                .build()
        );
    }
  }
}
