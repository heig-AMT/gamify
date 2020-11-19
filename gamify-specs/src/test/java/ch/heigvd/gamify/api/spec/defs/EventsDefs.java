package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.api.dto.Event;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.time.OffsetDateTime;

public class EventsDefs implements En {

  public EventsDefs(Environment environment) {
    Given("I have a event payload",
        () -> environment.getClient().putPayload("event", generatePayload())
    );
  }

  private Event generatePayload() {
    return new Event()
        .type("openQuestion")
        .userId("bob")
        .timestamp(OffsetDateTime.now());
  }
}
