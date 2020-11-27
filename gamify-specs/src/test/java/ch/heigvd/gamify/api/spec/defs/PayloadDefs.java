package ch.heigvd.gamify.api.spec.defs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.util.Collection;

public class PayloadDefs implements En {

  public PayloadDefs(Environment environment) {

    // Writing data to the server.
    When("I read the {word} payload", (String name) -> {
      environment.getClient().putPayload(name, environment.getClient().getResponseData());
    });

    When("I see that {word} and {word} are the same", (String first, String second) -> {
      var payload1 = environment.getClient().getPayload(first);
      var payload2 = environment.getClient().getPayload(second);

      assertEquals(payload1, payload2);
    });

    // Server responses management.
    Then("I receive a {int} status code", (Integer code) -> {
      assertEquals(code.intValue(), environment.getClient().getResponseStatus());
    });
    Then("I see {word} in {word}", (String element, String container) -> {
      var collection = (Collection) environment.getClient().getPayload(container);
      assertTrue(collection.contains(environment.getClient().getPayload(element)));
    });
  }
}
