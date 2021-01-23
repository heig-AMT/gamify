package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.EventsApi;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.endUser.EndUser;
import ch.heigvd.gamify.domain.endUser.EndUserIdentifier;
import ch.heigvd.gamify.domain.endUser.EndUserRepository;
import ch.heigvd.gamify.domain.endUserPointAward.EndUserPointAward;
import ch.heigvd.gamify.domain.endUserPointAward.EndUserPointAwardRepository;
import ch.heigvd.gamify.domain.event.Event;
import ch.heigvd.gamify.domain.event.EventRepository;
import ch.heigvd.gamify.domain.rule.Rule;
import ch.heigvd.gamify.domain.rule.RuleRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EventsController implements EventsApi {

  @Autowired
  EventRepository repository;

  @Autowired
  EndUserRepository userRepository;

  @Autowired
  EndUserPointAwardRepository userPointAwardRepository;

  @Autowired
  RuleRepository ruleRepository;

  @Autowired
  ServletRequest request;

  @Transactional
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
    var rules = ruleRepository.findByEventTypeAndId_App(event.getType(), app.getName());
    for (Rule r : rules) {
      addEventPointAward(event.getUserId(), app, r, r.getPoints());
    }
    return ResponseEntity.created(location.toUri()).build();
  }

  private void addEventPointAward(String username, App app, Rule rule, int points) {
    var endUser = userRepository
        .findByIdEndUser_UserIdAndIdEndUser_App(username, app)
        .orElse(userRepository.save(
            EndUser.builder()
                .idEndUser(
                    EndUserIdentifier.builder()
                        .userId(username)
                        .app(app).build()
                )
                .build())
        );

    userPointAwardRepository.save(
        EndUserPointAward.builder()
            .idxCategory(rule.getCategory())
            .idxEndUser(endUser)
            .points(points)
            .build()
    );
  }
}
