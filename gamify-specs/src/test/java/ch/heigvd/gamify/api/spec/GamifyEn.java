package ch.heigvd.gamify.api.spec;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.ApiResponse;
import ch.heigvd.gamify.api.DefaultApi;
import ch.heigvd.gamify.api.spec.env.Environment;
import io.cucumber.java8.En;

/**
 * An extension of the {@link En} language that contains some specific builders relevant to the
 * Gamify API.
 */
public interface GamifyEn extends En {

  /**
   * A functional interface that represents a request performed on the {@link DefaultApi}, and that
   * returns a specific body.
   *
   * @param <I> the body of the expected type of the request.
   * @param <O> the expected outpout for the request.
   */
  @FunctionalInterface
  interface Request<I, O> {

    /**
     * Performs the {@link Request}.
     *
     * @param body the body of the request.
     * @param api  the api on which to perform the request.
     * @return the {@link ApiResponse} if there was a success.
     * @throws ApiException if something went wrong.
     */
    ApiResponse<O> perform(I body, DefaultApi api) throws ApiException;
  }

  /**
   * Creates a new condition when a certain endpoint receives a POST request.
   *
   * @param endpoint    the endpoint at which the request is performed.
   * @param request     the {@link Request} mapper.
   * @param environment th {@link Environment} to perform the requests.
   * @param <I>         the type of the request body.
   * @param <O>         the type of the expected response body.
   */
  default <I, O> void WhenIPost(
      String endpoint,
      Request<I, O> request,
      Environment environment) {
    When("I POST the {word} payload to the " + endpoint + " endpoint", (String named) -> {
      var payload = environment.getClient().<I>getPayload(named);
      try {
        var res = request.perform(payload, environment.getApi());
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
}
