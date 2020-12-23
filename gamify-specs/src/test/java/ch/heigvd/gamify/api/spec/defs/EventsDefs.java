package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.EventsApi;
import ch.heigvd.gamify.api.dto.Event;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.time.OffsetDateTime;

public class EventsDefs implements En {

  public EventsDefs(Environment environment) {
    Given("I create the event payload {word}", (String name) ->
        environment.getClient().putPayload(name, generatePayload())
    );

    Given("I create the event payload {word} with type {word}", (String name, String type) ->
            environment.getClient().putPayload(name, eventForCategory(type))
    );

    Given("I create the event payload {word} with type {word} and for user {word}", (String name, String type, String userId) ->
            environment.getClient().putPayload(name, eventForCategoryAndUser(type, userId))
    );


    // Server I/O
    When("I POST the {word} payload to the api.events endpoint", (String named) -> {
      var payload = environment.getClient().<Event>getPayload(named);
      var api = new EventsApi(); // TODO : Is injection relevant here ?
      try {
        var info = api.addEventWithHttpInfo(payload);
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
        .type("openQuestion")
        .userId("bob")
        .timestamp(OffsetDateTime.now());
  }

  private Event eventForCategory(String type) {
    return new Event()
            .type(type)
            .userId("bob")
            .timestamp(OffsetDateTime.now());
  }

  private Event eventForCategoryAndUser(String type, String forUser) {
    return new Event()
            .type(type)
            .userId(forUser)
            .timestamp(OffsetDateTime.now());
  }
}
