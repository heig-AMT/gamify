package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;

public class AggregatesDefs implements En {

  public AggregatesDefs(Environment environment) {

    When("I GET from the api.leaderboards.{word} endpoint", (String name) -> {
      try {
        var api = new AggregatesApi();
        var categoryAggregate = api.getCategoryAggregateWithHttpInfo(name);
        environment.getClient().putResponse(
                categoryAggregate.getStatusCode(),
                categoryAggregate.getData()
        );
      } catch (ApiException exception) {
        environment.getClient().putResponse(
                exception.getCode(),
                exception.getResponseBody()
        );
      }
    });

    When("I GET from the api.users.{word} endpoint", (String id) -> {
      try {
        var api = new AggregatesApi();
        var userAggregate = api.getUserAggregateWithHttpInfo(name);
        environment.getClient().putResponse(
                userAggregate.getStatusCode(),
                userAggregate.getData()
        );
      } catch (ApiException exception) {
        environment.getClient().putResponse(
                exception.getCode(),
                exception.getResponseBody()
        );
      }
    });
  }
}
