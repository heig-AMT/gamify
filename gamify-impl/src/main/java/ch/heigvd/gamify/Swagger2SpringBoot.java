package ch.heigvd.gamify;

import ch.heigvd.gamify.domain.app.AppRepository;
import ch.heigvd.gamify.ui.api.filters.ApiKeyFilter;
import ch.heigvd.gamify.ui.api.filters.BasicAuthFilter;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"ch.heigvd.gamify", "ch.heigvd.gamify.api"})
public class Swagger2SpringBoot {

  @PostConstruct
  public void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  public static void main(String[] args) {
    new SpringApplication(Swagger2SpringBoot.class).run();
  }

  // AUTHENTICATION BEANS.

  /**
   * Creates a new {@link FilterRegistrationBean} that indicates that we'll actually apply the
   * {@link ApiKeyFilter} on some specific paths.
   *
   * @param repository the {@link AppRepository} that the registration filter depends on.
   */
  @Bean
  public FilterRegistrationBean<ApiKeyFilter> apiKey(
      AppRepository repository
  ) {
    var bean = new FilterRegistrationBean<ApiKeyFilter>();
    bean.setFilter(new ApiKeyFilter(repository));
    // TODO : Add your authenticated endpoints here.

    bean.addUrlPatterns("/events");
    bean.addUrlPatterns("/categories");
    bean.addUrlPatterns("/categories/*");
    return bean;
  }

  @Bean
  public FilterRegistrationBean<BasicAuthFilter> basicAuth(
      AppRepository repository
  ) {
    var bean = new FilterRegistrationBean<BasicAuthFilter>();
    bean.setFilter(new BasicAuthFilter(repository));
    bean.addUrlPatterns("/account");
    bean.addUrlPatterns("/account/token");
    return bean;
  }
}
