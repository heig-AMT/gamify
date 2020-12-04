package ch.heigvd.gamify.ui.web;

import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;

public class SecurityReferences {

  private SecurityReferences() {
    // hidden.
  }

  public static SecurityReference apiKey() {
    return new SecurityReference("key", new AuthorizationScope[0]);
  }

  public static SecurityReference basicAuth() {
    return new SecurityReference("basic", new AuthorizationScope[0]);
  }
}
