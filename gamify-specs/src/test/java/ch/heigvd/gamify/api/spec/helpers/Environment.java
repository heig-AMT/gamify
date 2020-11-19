package ch.heigvd.gamify.api.spec.helpers;

import ch.heigvd.gamify.api.DefaultApi;

import java.io.IOException;
import java.util.Properties;
import java.util.TimeZone;

public class Environment {

  private final DefaultApi api = new DefaultApi();

  public Environment() throws IOException {
    Properties properties = new Properties();
    properties.load(getClass().getClassLoader().getResourceAsStream("environment.properties"));
    String url = properties.getProperty("ch.heigvd.gamify.server.url");
    api.getApiClient().setBasePath(url);
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  public DefaultApi getApi() {
    return api;
  }
}
