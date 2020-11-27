package ch.heigvd.gamify.api.spec.defs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import ch.heigvd.gamify.api.dto.Event;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import io.cucumber.java8.ParameterDefinitionBody.A1;
import java.util.Collection;
import org.junit.Assert;

public class PayloadDefs implements En {

  private enum Write {
    POST,
  }

  private enum Read {
    GET,
  }

  public PayloadDefs(Environment environment) {

    // Request methods definitions.
    ParameterType("write", "POST|PUT", (A1<Write>) Write::valueOf);
    ParameterType("read", "GET", (A1<Read>) Read::valueOf);

    // Writing data to the server.
    When("I {write} the {word} payload to the /events endpoint", (Write method, String named) -> {
      var payload = environment.getClient().<Event>getPayload(named);
      switch (method) {
        case POST:
          var info = environment.getApi().addEventWithHttpInfo(payload);
          environment.getClient().putResponse(
              info.getStatusCode(),
              info.getData()
          );
          break;
        default:
          throw new RuntimeException("Unknown method " + method);
      }
    });
    When("I {read} from the /events endpoint", (Read method) -> {
      switch (method) {
        case GET:
          var info = environment.getApi().getEventsWithHttpInfo();
          environment.getClient().putResponse(
              info.getStatusCode(),
              info.getData()
          );
          break;
        default:
          throw new RuntimeException("Unknown method " + method);
      }
    });
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
