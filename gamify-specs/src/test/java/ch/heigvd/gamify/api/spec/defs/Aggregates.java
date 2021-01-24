package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.AggregatesApi;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.util.List;

public class Aggregates implements En {

  public Aggregates(Environment environment) {

    When("I GET from the api.leaderboards.{word} endpoint", (String name) -> {
      try {
        var api = new AggregatesApi();
        var categoryAggregate = api.getLeaderboardWithHttpInfo(name, 0, Integer.MAX_VALUE);
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

    When("I GET from the api.leaderboards.{word} endpoint with no pagination", (String name) -> {
      try {
        var api = new AggregatesApi();
        var categoryAggregate = api.getLeaderboardWithHttpInfo(name, null, null);
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

    When("I GET from the api.leaderboards.{word} endpoint for page {int} and page size {int}",
        (String name, Integer page, Integer pagesize) -> {
          try {
            var api = new AggregatesApi();
            var categoryAggregate = api.getLeaderboardWithHttpInfo(name, page, pagesize);
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
        var userAggregate = api.getUserAggregateWithHttpInfo(id, List.of());
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

    When("I GET from the api.users.{word} endpoint for category {word}",
        (String id, String category) -> {
          try {
            var api = new AggregatesApi();
            var userAggregate = api.getUserAggregateWithHttpInfo(id, List.of(category));
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
