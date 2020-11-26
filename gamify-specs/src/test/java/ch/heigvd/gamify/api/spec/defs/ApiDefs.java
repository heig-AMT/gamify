package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;

public class ApiDefs implements En {

  public ApiDefs(Environment environment) {
    Then("I read the {word} payload as the {word} property of the {word} payload",
        (String field, String named, String original) -> {
          var payload = environment.getClient().getPayload(original);
          System.out.println(payload.getClass());
          var innerFiled = payload.getClass().getDeclaredField(named);
          innerFiled.setAccessible(true);
          environment.getClient().putPayload(field, innerFiled.get(payload));
        });
  }
}
