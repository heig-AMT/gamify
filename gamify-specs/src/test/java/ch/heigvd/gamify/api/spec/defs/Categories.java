package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.CategoriesApi;
import ch.heigvd.gamify.api.dto.Category;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;
import java.util.UUID;

public class Categories implements En {

  public Categories(Environment environment) {
    When("I create the category payload {word}", (String named) -> {
      environment.getClient().putPayload(named, randomCategory());
    });
    When("I create the category payload {word} with name {word}", (String named, String name) -> {
      environment.getClient().putPayload(named, namedCategory(name));
    });

    When("I GET the payload from the api.categories endpoint", () -> {
      var api = new CategoriesApi();
      try {
        var categories = api.getCategoriesWithHttpInfo(0, Integer.MAX_VALUE);
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
    When("I POST the {word} payload to the api.categories endpoint", (String named) -> {
      var api = new CategoriesApi();
      try {
        var category = api.postCategoryWithHttpInfo(environment.getClient().getPayload(named));
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

    When("I PUT the {word} resource to the api.categories.*** endpoint", (String named) -> {
      Category payload = environment.getClient().getPayload(named);
      var api = new CategoriesApi();
      try {
        // TODO : Eventually change the spec.
        var info = api.putCategoryWithHttpInfo(payload.getName(), payload);
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

    When("I PUT the {word} payload to the api.categories.{word} endpoint",
        (String named, String id) -> {
          Category payload = environment.getClient().getPayload(named);
          var api = new CategoriesApi(); // TODO : Is injection relevant here ?
          try {
            // TODO : Eventually change the spec.
            var info = api.putCategoryWithHttpInfo(id, payload);
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

    When("I GET from the api.categories.{word} endpoint", (String id) -> {
      try {
        var api = new CategoriesApi();
        var category = api.getCategoryWithHttpInfo(id);
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

    When("I DELETE the resource api.categories.{word}", (String id) -> {
      try {
        var api = new CategoriesApi();
        var res = api.deleteCategoryWithHttpInfo(id);
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

  private Category randomCategory() {
    return new Category()
        .title(UUID.randomUUID().toString())
        .name(UUID.randomUUID().toString())
        .description(UUID.randomUUID().toString());
  }

  private Category namedCategory(String name) {
    return randomCategory()
        .name(name);
  }
}
