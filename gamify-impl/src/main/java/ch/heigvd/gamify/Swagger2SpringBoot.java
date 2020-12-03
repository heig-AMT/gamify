package ch.heigvd.gamify;

import ch.heigvd.gamify.api.filters.ApiKeyFilter;
import ch.heigvd.gamify.api.filters.BasicAuthFilter;
import ch.heigvd.gamify.repositories.RegisteredAppRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@ComponentScan(basePackages = {"ch.heigvd.gamify", "ch.heigvd.gamify.api"})
public class Swagger2SpringBoot implements CommandLineRunner {

  @PostConstruct
  void started() {
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
  }

  @Override
  public void run(String... arg0) {
    if (arg0.length > 0 && arg0[0].equals("exitcode")) {
      throw new ExitException();
    }
  }

  public static void main(String[] args) {
    new SpringApplication(Swagger2SpringBoot.class).run(args);
  }

  // AUTHENTICATION BEANS.

  /**
   * Creates a new {@link FilterRegistrationBean} that indicates that we'll actually apply the
   * {@link ApiKeyFilter} on some specific paths.
   *
   * @param repository the {@link RegisteredAppRepository} that the registration filter depends on.
   */
  @Bean
  public FilterRegistrationBean<ApiKeyFilter> apiKey(
      RegisteredAppRepository repository
  ) {
    var bean = new FilterRegistrationBean<ApiKeyFilter>();
    bean.setFilter(new ApiKeyFilter(repository));
    // TODO : Add your authenticated endpoints here.

    bean.addUrlPatterns("/events");
    return bean;
  }

  @Bean
  public FilterRegistrationBean<BasicAuthFilter> basicAuth(
      RegisteredAppRepository repository
  ) {
    var bean = new FilterRegistrationBean<BasicAuthFilter>();
    bean.setFilter(new BasicAuthFilter(repository));
    bean.addUrlPatterns("/account");
    return bean;
  }

  /**
   * An implementation of a {@link RuntimeException} that is used for exit.
   */
  static class ExitException extends RuntimeException implements ExitCodeGenerator {

    private static final long serialVersionUID = 1L;

    @Override
    public int getExitCode() {
      return 10;
    }
  }
}
