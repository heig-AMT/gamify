package ch.heigvd.gamify.ui.web;

import static ch.heigvd.gamify.ui.web.SecuritySchemes.apiKey;
import static ch.heigvd.gamify.ui.web.SecuritySchemes.basicAuth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

/**
 * The springfox configuration for our API description.
 */
@Configuration
public class Swagger {

  // API description constants.
  private static final String API_DESC_VERSION = "0.1.0";
  private static final String API_DESC_NAME = "Gamify API";
  private static final String API_DESC_BODY = "An API that manages application-specific events,"
      + " and lets you define rules to find user scores.";

  // General API info.
  private static final ApiInfo API_INFO = new ApiInfoBuilder()
      .title(API_DESC_NAME)
      .description(API_DESC_BODY)
      .version(API_DESC_VERSION)
      .build();

  /**
   * Returns the {@link Docket} that describes our API, by introspecting the request handlers
   * defined in the {@link ch.heigvd.gamify.ui.api} package.
   */
  @Bean
  public Docket customImplementation() {
    return new Docket(DocumentationType.OAS_30)
        .select()
        .apis(RequestHandlerSelectors.basePackage("ch.heigvd.gamify.ui.api"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(API_INFO)
        .securitySchemes(List.of(apiKey(), basicAuth()))
        .securityContexts(List.of(SecurityContext.builder()
            .securityReferences(
                List.of(SecurityReferences.apiKey(),
                    SecurityReferences.basicAuth()))
            .build()));

  }

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
      }
    };
  }
}
