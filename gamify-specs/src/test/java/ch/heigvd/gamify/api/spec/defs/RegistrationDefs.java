package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.dto.Registration;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.util.UUID;

public class RegistrationDefs implements En {

  public RegistrationDefs(Environment environment) {
    Given("I create the registration payload {word}",
        (String name) -> environment.getClient().putPayload(name, generateRegistration())
    );

    When("I POST the {word} payload to the /register endpoint", (String named) -> {
      var payload = environment.getClient().<Registration>getPayload(named);
      try {
        var info = environment.getApi().addAppWithHttpInfo(payload);
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

  private Registration generateRegistration() {
    return new Registration()
        .appId(UUID.randomUUID().toString())
        .password(UUID.randomUUID().toString());
  }
}
