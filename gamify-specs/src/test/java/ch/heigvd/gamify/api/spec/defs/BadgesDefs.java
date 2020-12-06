package ch.heigvd.gamify.api.spec.defs;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.api.BadgesApi;
import ch.heigvd.gamify.api.dto.Badge;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;

public class BadgesDefs implements En
{
    public BadgesDefs(Environment environment)
    {
        Given("I create the badge {word} linked to category {word}", (String badge, String category) ->
                environment.getClient().putPayload(badge, newBadge(badge, category)));

        When("I PUT the {word} badge to the api\\.badges endpoint", (String badgeName) ->
        {
            var payload = environment.getClient().<Badge>getPayload(badgeName);
            var api = new BadgesApi();
            try {
                var info = api.putBadgeWithHttpInfo(badgeName, payload);
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
        When("I just GET from the badges endpoint", () ->
        {
            var api = new BadgesApi();
            try {
                var info = api.getBadgesWithHttpInfo();
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
        When("I GET {word} from the badges endpoint", (String name) ->
        {
            var api = new BadgesApi();
            try {
                var info = api.getBadgeWithHttpInfo(name);
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

    private Badge newBadge(String name,String catName){
        return new Badge().category(catName).name(name);
    }
}
