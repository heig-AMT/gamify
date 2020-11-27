package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.dto.Category;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;

public class CategoriesDef implements En
{
    public CategoriesDef(Environment environment){
        Given("I create the category payload {word}",
                (String catName)->environment.getClient().putPayload(catName,generateCategory(catName)));

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
    }

    private Category generateCategory(String name){
        return new Category()
                .name(name);
    }
}
