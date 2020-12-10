package ch.heigvd.gamify.ui.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * A Spring {@link Configuration} for Jackson serializing and deserializing.
 */
@Configuration
public class JacksonConfiguration {

  /**
   * Adds support for {@link org.openapitools.jackson.nullable.JsonNullable} in our API requests and
   * responses.
   *
   * @param mapper the Jackson {@link ObjectMapper}.
   */
  @Autowired
  void configureObjectMapper(final ObjectMapper mapper) {
    mapper.registerModule(new JsonNullableModule());
  }
}
