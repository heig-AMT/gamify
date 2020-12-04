package ch.heigvd.gamify.ui.web;

import springfox.documentation.service.ApiKey;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;

/**
 * A class with the different supported security schemes of the Gamify application.
 */
public class SecuritySchemes {

  public static final String API_KEY_NAME = "key";
  public static final String BASIC_AUTH_NAME = "basic";

  private SecuritySchemes() {
    // hidden.
  }

  /**
   * Returns the {@link SecurityScheme} that is used to authenticate generic data management
   * requests.
   */
  public static SecurityScheme apiKey() {
    return new ApiKey(API_KEY_NAME, "X-API-KEY", "header");
  }

  /**
   * Returns the {@link SecurityScheme} that is used to perform account-related operations.
   */
  public static SecurityScheme basicAuth() {
    return new BasicAuth(BASIC_AUTH_NAME);
  }
}
