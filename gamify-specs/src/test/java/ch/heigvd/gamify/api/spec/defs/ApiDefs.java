package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.api.spec.env.Environment;
import ch.heigvd.gamify.auth.ApiKeyAuth;
import io.cucumber.java8.En;

public class ApiDefs implements En {

  public ApiDefs(Environment environment) {
    Then("I authenticate with {word} payload", (String named) -> {
      var token = environment.getClient().getPayload(named);
      var auth = (ApiKeyAuth) environment.getApi()
          .getApiClient()
          .getAuthentication("apiKeyAuth");
      auth.setApiKey((String) token);
    });
    Then("I don't authenticate", () -> {
      var auth = (ApiKeyAuth) environment.getApi()
          .getApiClient()
          .getAuthentication("apiKeyAuth");
      auth.setApiKey("");
    });
    Then("I read the {word} payload as the {word} property of the {word} payload",
        (String field, String named, String original) -> {
          var payload = environment.getClient().getPayload(original);
          var innerField = payload.getClass().getDeclaredField(named);
          innerField.setAccessible(true);
          environment.getClient().putPayload(field, innerField.get(payload));
        });
  }
}
