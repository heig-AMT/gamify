package ch.heigvd.gamify.api.spec.steps;

import ch.heigvd.gamify.ApiException;
import ch.heigvd.gamify.ApiResponse;
import ch.heigvd.gamify.api.DefaultApi;
import ch.heigvd.gamify.api.dto.Fruit;
import ch.heigvd.gamify.api.spec.helpers.Environment;
import io.cucumber.java8.En;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BasicSteps implements En {

  private Environment environment;
  private DefaultApi api;

  Fruit fruit;

  private ApiResponse lastApiResponse;
  private ApiException lastApiException;
  private boolean lastApiCallThrewException;
  private int lastStatusCode;

  private String lastReceivedLocationHeader;
  private Fruit lastReceivedFruit;

  public BasicSteps(Environment environment) {
    this.environment = environment;
    this.api = environment.getApi();

    Given("there is a Fruits server", () -> assertNotNull(api));
    Given("I have a fruit payload", () -> {
      fruit = new ch.heigvd.gamify.api.dto.Fruit()
          .kind("banana")
          .colour("yellow")
          .size("medium")
          .weight("light")
          .expirationDate(LocalDate.now())
          .expirationDateTime(OffsetDateTime.now());
    });
    When("^I POST the fruit payload to the /fruits endpoint$", () -> {
      try {
        lastApiResponse = api.createFruitWithHttpInfo(fruit);
        processApiResponse(lastApiResponse);
      } catch (ApiException e) {
        processApiException(e);
      }
    });
    When("I receive a {int} status code", (Integer code) -> {
      assertEquals(code.intValue(), lastStatusCode);
    });
    When("^I send a GET to the /fruits endpoint$", () -> {
      try {
        lastApiResponse = api.getFruitsWithHttpInfo();
        processApiResponse(lastApiResponse);
      } catch (ApiException e) {
        processApiException(e);
      }
    });
    Then("I receive a {int} status code with a location header", (Integer code) -> {
    });
    When("I send a GET to the URL in the location header", () -> {
      Integer id = Integer.parseInt(
          lastReceivedLocationHeader.substring(lastReceivedLocationHeader.lastIndexOf('/') + 1));
      try {
        lastApiResponse = api.getFruitWithHttpInfo(id);
        processApiResponse(lastApiResponse);
        lastReceivedFruit = (Fruit) lastApiResponse.getData();
      } catch (ApiException e) {
        processApiException(e);
      }
    });
    And("I receive a payload that is the same as the fruit payload", () -> {
      assertEquals(fruit, lastReceivedFruit);
    });
  }

  private void processApiResponse(ApiResponse apiResponse) {
    lastApiResponse = apiResponse;
    lastApiCallThrewException = false;
    lastApiException = null;
    lastStatusCode = lastApiResponse.getStatusCode();
    List<String> locationHeaderValues = (List<String>) lastApiResponse.getHeaders().get("Location");
    lastReceivedLocationHeader = locationHeaderValues != null ? locationHeaderValues.get(0) : null;
  }

  private void processApiException(ApiException apiException) {
    lastApiCallThrewException = true;
    lastApiResponse = null;
    lastApiException = apiException;
    lastStatusCode = lastApiException.getCode();
  }

}
