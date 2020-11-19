package ch.heigvd.gamify.api.spec.env;

import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

public class Client {

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
}
