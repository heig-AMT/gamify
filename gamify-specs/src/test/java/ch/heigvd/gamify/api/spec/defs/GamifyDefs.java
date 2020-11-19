package ch.heigvd.gamify.api.spec.defs;

import static org.junit.Assert.assertNotNull;

import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;

public class GamifyDefs implements En {

  public GamifyDefs(Environment environment) {
    Given("there is a Gamify server", () -> assertNotNull(environment.getApi()));
  }
}
