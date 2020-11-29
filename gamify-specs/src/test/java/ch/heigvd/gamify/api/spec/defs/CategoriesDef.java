package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.dto.Category;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;

public class CategoriesDef implements En
{
    public CategoriesDef(Environment environment){
        Given("I create the category payload named {word} described as {word}",
                (String catName, String descr)->environment.getClient().putPayload(catName,generateCategory(catName, descr)));

        When("I POST the {word} payload to the /categories endpoint", (String category) ->
        {var payload=environment.getClient().<Category>getPayload(category);
            try {
                var info = environment.getApi().addCategoryWithHttpInfo(payload);
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

        When("I GET the {word} payload to the /categories endpoint", (String category) ->
        {var payload=environment.getClient().<Category>getPayload(category);
                var info = environment.getApi().getCategoryWithHttpInfo(category);
                environment.getClient().putResponse(
                        info.getStatusCode(),
                        info.getData()
                );
        });

        When("I UPDATE {word} to {word} to the /categories endpoint", (String categoryToUpdate, String newCategory) ->
        {var payload=environment.getClient().<Category>getPayload(newCategory);
            try {
                var info = environment.getApi().updateCategoryWithHttpInfo(categoryToUpdate, payload);
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

        When("I DELETE the {word} payload to the /categories endpoint", (String category) ->
        {
            var info = environment.getApi().deleteCategoryWithHttpInfo(category);
            environment.getClient().putResponse(
                    info.getStatusCode(),
                    info.getData()
            );
        });
    }

    private Category generateCategory(String name, String description){
        return new Category()
                .name(name)
                .title(name)
                .description(description);
    }
}
