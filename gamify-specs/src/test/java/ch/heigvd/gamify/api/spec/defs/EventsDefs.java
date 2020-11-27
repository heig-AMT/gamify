package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
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

    // Server I/O
    When("I POST the {word} payload to the /events endpoint", (String named) -> {
      var payload = environment.getClient().<Event>getPayload(named);
      try {
        var info = environment.getApi().addEventWithHttpInfo(payload);
        environment.getClient().putResponse(
            info.getStatusCode(),
            info.getData()
        );
      } catch (ApiException exception) {
        environment.getClient().putResponse(
            exception.getCode(),
            exception.getResponseBody()
        );
      }
    });
  }

  private Event generatePayload() {
    return new Event()
        .type("openQuestion"+ UUID.randomUUID().toString())
        .userId("bob")
        .timestamp(OffsetDateTime.now());
  }
}
