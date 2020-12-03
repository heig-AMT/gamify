package ch.heigvd.gamify.api.spec.env;

import ch.heigvd.gamify.Configuration;
import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

public class Environment {

  private static final String PROPERTY_FILE = "environment.properties";
  private static final String PROPERTY_URL = "ch.heigvd.gamify.server.url";

  private final Client client = new Client();

  public Environment() throws IOException {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

    prepareApiClient();
  }

  private void prepareApiClient() throws IOException {
    var properties = new Properties();
    var resource = getClass().getClassLoader().getResourceAsStream(PROPERTY_FILE);
    properties.load(resource);
    var url = properties.getProperty(PROPERTY_URL);

    // Set the API client URL.
    Configuration.getDefaultApiClient().setBasePath(url);
  }

  public Client getClient() {
    return client;
  }
}
