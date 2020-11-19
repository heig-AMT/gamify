package ch.heigvd.gamify.api.spec.steps;

import static org.junit.Assert.assertNotNull;

import ch.heigvd.gamify.api.dto.Event;
import ch.heigvd.gamify.api.dto.Registration;
import ch.heigvd.gamify.api.spec.helpers.Environment;
import io.cucumber.java8.En;
import java.time.OffsetDateTime;

public class EventsStepsDefinitions implements En {

  // Current scenario properties.
  private Event event;
  private Registration registration;

  public EventsStepsDefinitions(Environment environment) {

    // Prepare the different items for running scenarios.
    var api = environment.getApi();

    // Preconditions.
    Given("there is a Gamify server", () -> assertNotNull(api));
    Given("I have a event payload", () -> event = generatePayload());
    Given("I have a registration payload", () -> registration = generateRegistration());

    // Assertions.
    When("I POST the event payload to the /events endpoint", () -> {
      // TODO : Do something with this info.
      var info = api.addEventWithHttpInfo(event);
    });
  }

  private Event generatePayload() {
    return new Event()
        .type("openQuestion")
        .userId("bob")
        .timestamp(OffsetDateTime.now());
  }

  private Registration generateRegistration() {
    return new Registration()
        .appId("stackunderflow")
        .password("password");
  }
}
