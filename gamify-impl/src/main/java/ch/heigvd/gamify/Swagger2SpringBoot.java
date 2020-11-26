package ch.heigvd.gamify;

import ch.heigvd.gamify.api.filters.RegistrationFilter;
import ch.heigvd.gamify.repositories.RegisteredAppRepository;
import java.util.List;
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

  // API KEY BEANS.

  /**
   * Creates a new {@link FilterRegistrationBean} that indicates that we'll actually apply the
   * {@link RegistrationFilter} on some specific paths.
   *
   * @param repository the {@link RegisteredAppRepository} that the registration filter depends on.
   */
  @Bean
  public FilterRegistrationBean<RegistrationFilter> registration(
      RegisteredAppRepository repository
  ) {
    var bean = new FilterRegistrationBean<RegistrationFilter>();
    bean.setFilter(new RegistrationFilter(repository));

    // TODO : Add your authenticated endpoints here.
    bean.addUrlPatterns("/events");

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
