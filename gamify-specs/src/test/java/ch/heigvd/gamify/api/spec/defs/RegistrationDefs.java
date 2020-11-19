package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.api.dto.Registration;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;

public class RegistrationDefs implements En {

  public RegistrationDefs(Environment environment) {
    Given("I have a registration payload",
        () -> environment.getClient().putPayload("registration", generateRegistration())
    );
  }

  private Registration generateRegistration() {
    return new Registration()
        .appId("stackunderflow")
        .password("password");
  }
}
