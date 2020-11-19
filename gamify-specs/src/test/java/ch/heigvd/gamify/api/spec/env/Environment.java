package ch.heigvd.gamify.api.spec.env;

import ch.heigvd.gamify.api.DefaultApi;

import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

public class Environment {

  private final DefaultApi api = new DefaultApi();
  private final Client client = new Client();

  public Environment() throws IOException {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    generateApi();
  }

  public DefaultApi getApi() {
    return api;
  }

  public Client getClient() {
    return client;
  }

  private void generateApi() throws IOException {
    var properties = new Properties();
    var resource = getClass().getClassLoader()
        .getResourceAsStream("environment.properties");
    properties.load(resource);
    var url = properties.getProperty("ch.heigvd.gamify.server.url");

    // Set the API client URL.
    api.getApiClient().setBasePath(url);
  }
}
