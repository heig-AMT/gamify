package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.RulesApi;
import ch.heigvd.gamify.api.dto.Rule;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.util.Random;
import java.util.UUID;

public class Rules implements En {

  public Rules(Environment environment) {
    When("I create the rule payload {word} for category {word}",
        (String named, String category) -> environment.getClient()
            .putPayload(named, randomRule(category)));
    When("I create the category payload {word} with name {word} for category {word}",
        (String named, String name, String category) -> environment.getClient()
            .putPayload(named, namedRule(name, category)));

    When("I GET the payload from the api.rules endpoint", () -> {
      var api = new RulesApi();
      try {
        var categories = api.getRulesWithHttpInfo();
        environment.getClient().putResponse(
            categories.getStatusCode(),
            categories.getData()
        );
      } catch (ApiException exception) {
        environment.getClient().putResponse(
            exception.getCode(),
            exception.getResponseBody()
        );
      }
    });

    When("I POST the {word} payload to the api.rules endpoint", (String named) -> {
      var api = new RulesApi();
      try {
        var category = api.postRuleWithHttpInfo(environment.getClient().getPayload(named));
        environment.getClient().putResponse(
            category.getStatusCode(),
            category.getData()
        );
      } catch (ApiException exception) {
        environment.getClient().putResponse(
            exception.getCode(),
            exception.getResponseBody()
        );
      }
    });

    When("I PUT the {word} resource to the api.rules.*** endpoint", (String named) -> {
      Rule payload = environment.getClient().getPayload(named);
      var api = new RulesApi();
      try {
        var info = api.putRuleWithHttpInfo(payload.getName(), payload);
        environment.getClient().putResponse(
            info.getStatusCode(),
            info.getData()
        );
      } catch (ApiException exception) {
        environment.getClient().putResponse(
            exception.getCode(),
            exception.getResponseBody()
        );
      }
    });

    When("I PUT the {word} payload to the api.rules.{word} endpoint", (String named, String id) -> {
      Rule payload = environment.getClient().getPayload(named);
      var api = new RulesApi();
      try {
        var info = api.putRuleWithHttpInfo(id, payload);
        environment.getClient().putResponse(
            info.getStatusCode(),
            info.getData()
        );
      } catch (ApiException exception) {
        environment.getClient().putResponse(
            exception.getCode(),
            exception.getResponseBody()
        );
      }
    });

    When("I GET from the api.rules.{word} endpoint", (String id) -> {
      try {
        var api = new RulesApi();
        var category = api.getRuleWithHttpInfo(id);
        environment.getClient().putResponse(
            category.getStatusCode(),
            category.getData()
        );
      } catch (ApiException exception) {
        environment.getClient().putResponse(
            exception.getCode(),
            exception.getResponseBody()
        );
      }
    });

    When("I DELETE the resource api.rules.{word}", (String id) -> {
      try {
        var api = new RulesApi();
        var res = api.deleteRuleWithHttpInfo(id);
        environment.getClient().putResponse(
            res.getStatusCode(),
            res.getData()
        );
      } catch (ApiException exception) {
        environment.getClient().putResponse(
            exception.getCode(),
            exception.getResponseBody()
        );
      }
    });
  }

  private Rule randomRule(String forCategory) {
    var random = new Random();
    return new Rule()
        .event(UUID.randomUUID().toString())
        .points(random.nextInt())
        .name(UUID.randomUUID().toString())
        .category(forCategory);
  }

  private Rule namedRule(String name, String forCategory) {
    return randomRule(forCategory)
        .name(name);
  }
}
