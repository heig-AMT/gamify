package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.dto.Credentials;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.util.UUID;

public class CredentialsDefs implements En {

  public CredentialsDefs(Environment environment) {
    Given("I create the credentials payload {word}",
        (String name) -> environment.getClient().putPayload(name, generateCredentials())
    );

    When("I POST the {word} payload to the /register endpoint", (String named) -> {
      var payload = environment.getClient().<Credentials>getPayload(named);
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

    When("I POST the {word} payload to the /login endpoint", (String named) -> {
      var payload = environment.getClient().<Credentials>getPayload(named);
      try {
        var info = environment.getApi().loginWithHttpInfo(payload);
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

  private Credentials generateCredentials() {
    return new Credentials()
        .appId(UUID.randomUUID().toString())
        .password(UUID.randomUUID().toString());
  }
}
