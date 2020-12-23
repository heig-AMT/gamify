package ch.heigvd.gamify.api.spec.defs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import ch.heigvd.gamify.api.dto.Ranking;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.util.Collection;
import java.util.List;

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

    When("I see that {word} and {word} are not the same", (String first, String second) -> {
      var payload1 = environment.getClient().getPayload(first);
      var payload2 = environment.getClient().getPayload(second);

      assertNotEquals(payload1, payload2);
    });

    // Server responses management.
    Then("I receive a {int} status code", (Integer code) -> {
      assertEquals(code.intValue(), environment.getClient().getResponseStatus());
    });
    Then("I see {word} in {word}", (String element, String container) -> {
      var collection = (Collection) environment.getClient().getPayload(container);
      assertTrue(collection.contains(environment.getClient().getPayload(element)));
    });
    Then("I count {int} items in {word}", (Integer count, String container) -> {
      var collection = (Collection) environment.getClient().getPayload(container);
      assertEquals(count.intValue(), collection.size());
    });
    Then("I read the {word} payload as the {word} property of the {word} payload",
        (String field, String named, String original) -> {
          var payload = environment.getClient().getPayload(original);
          var innerField = payload.getClass().getDeclaredField(named);
          innerField.setAccessible(true);
          environment.getClient().putPayload(field, innerField.get(payload));
        });

    Then("The best user in the leaderboard {word} has {int} points",
            (String container, Integer points) -> {
              var list = (List) environment.getClient().getPayload(container);
              var ranking = (Ranking) list.get(0);
              assertEquals(points, ranking.getPoints());
    });

      Then("The first point category in the user ranking {word} has {int} points",
              (String container, Integer points, String category) -> {
                  var list = (List) environment.getClient().getPayload(container);
                  var ranking = (Ranking) list.get(0);
                  assertEquals(points, ranking.getPoints());
              });
  }
}
