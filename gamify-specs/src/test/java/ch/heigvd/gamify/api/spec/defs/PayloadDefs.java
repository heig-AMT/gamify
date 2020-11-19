package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.api.dto.Event;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import io.cucumber.java8.ParameterDefinitionBody.A1;

public class PayloadDefs implements En {

  private enum Write {
    POST,
  }

  public PayloadDefs(Environment environment) {

    // Request methods definitions.
    ParameterType("write", "POST|PUT", (A1<Write>) Write::valueOf);

    // Writing data to the server.
    When("I {write} the {word} payload to the /events endpoint", (Write method, String named) -> {
      var payload = environment.getClient().<Event>getPayload(named);
      switch (method) {
        case POST:
          environment.getApi().addEventWithHttpInfo(payload);
          break;
        default:
          throw new RuntimeException("Unknown method " + method);
      }
    });
  }
}
