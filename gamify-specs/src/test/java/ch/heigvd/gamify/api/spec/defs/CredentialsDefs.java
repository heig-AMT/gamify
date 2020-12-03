package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.AccountApi;
import ch.heigvd.gamify.api.dto.Registration;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.util.UUID;

public class CredentialsDefs implements En {

  public CredentialsDefs(Environment environment) {
    Given("I create the credentials payload {word}",
        (String name) -> environment.getClient().putPayload(name, generateRegistration())
    );

    When("I POST the {word} payload to the /register endpoint", (String named) -> {
      var payload = environment.getClient().<Registration>getPayload(named);
      var api = new AccountApi();
      try {
        var info = api.registerAccountWithHttpInfo(payload);
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

    When("I POST to the /login endpoint", () -> {
      try {
        var api = new AccountApi();
        var info = api.loginWithHttpInfo();
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
        .username(UUID.randomUUID().toString())
        .password(UUID.randomUUID().toString());
  }
}
