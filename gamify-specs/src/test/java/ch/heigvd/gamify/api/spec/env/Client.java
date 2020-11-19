package ch.heigvd.gamify.api.spec.env;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

public class Client {

  // PAYLOAD MANAGEMENT

  private final Map<String, Object> payloads = new HashMap<>();

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

  // RESPONSE STATUS AND CODE MANAGEMENT

  private Integer status = null;

  /**
   * Puts a certain response status code in the {@link Client}.
   *
   * @param code the retrieved code.
   */
  public void putResponseStatus(int code) {
    this.status = code;
  }

  /**
   * Returns the last set response status.
   */
  public int getResponseStatus() {
    assertNotNull(this.status);
    return this.status;
  }
}
