package ch.heigvd.gamify.api.spec.defs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import io.cucumber.java8.ParameterDefinitionBody.A1;
import java.util.Collection;

public class PayloadDefs implements En {

  private enum Read {
    GET,
  }

  public PayloadDefs(Environment environment) {

    // Request methods definitions.
    ParameterType("read", "GET", (A1<Read>) Read::valueOf);

    // Writing data to the server.
    When("I read the {word} payload", (String name) -> {
      environment.getClient().putPayload(name, environment.getClient().getResponseData());
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
