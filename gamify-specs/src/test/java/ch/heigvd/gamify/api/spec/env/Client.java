package ch.heigvd.gamify.api.spec.env;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

public class Client {

  // PAYLOAD MANAGEMENT

  private final Map<String, Object> payloads = new HashMap<>();
  private Integer responseStatus = null;
  private Object responseData = null;

  // RESPONSE STATUS AND CODE MANAGEMENT

  /**
   * Puts a certain payload for a matching key.
   *
   * @param name the name of the payload.
   * @param data the payload data.
   * @param <T>  the type of the payload.
   */
  public <T> void putPayload(String name, T data) {
    payloads.put(name, data);
  }

  /**
   * Retrieves a certain payload for a matching key.
   *
   * @param name the name of the payload.
   * @param <T>  the type of the payload.
   * @return the payload data.
   */
  @SuppressWarnings("unchecked")
  public <T> T getPayload(String name) {
    var payload = payloads.get(name);
    assertNotNull(payload);
    return (T) payload;
  }

  /**
   * Puts a certain response status code in the {@link Client}.
   *
   * @param code the retrieved code.
   * @param data the response data.
   */
  public void putResponse(int code, Object data) {
    this.responseStatus = code;
    this.responseData = data;
  }

  /**
   * Returns the last set response status.
   */
  public int getResponseStatus() {
    assertNotNull(this.responseStatus);
    return this.responseStatus;
  }

  /**
   * Returns the last set response data.
   *
   * @param <T> the type of the response data.
   */
  @SuppressWarnings("unchecked")
  public <T> T getResponseData() {
    assertNotNull(responseData);
    return (T) responseData;
  }
}
