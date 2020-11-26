package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.api.dto.Event;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.time.OffsetDateTime;
import java.util.UUID;

public class EventsDefs implements En {

  public EventsDefs(Environment environment) {
    Given("I create the event payload {word}", (String name) ->
        environment.getClient().putPayload(name, generatePayload())
    );
  }

  private Event generatePayload() {
    return new Event()
        .type("openQuestion"+ UUID.randomUUID().toString())
        .userId("bob")
        .timestamp(OffsetDateTime.now());
  }
}
