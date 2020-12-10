package ch.heigvd.gamify.ui.api.filters;

import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.app.AppRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicAuthFilter implements Filter {

  /**
   * The key in which the {@link App} will be stored.
   */
  public static final String APP_KEY = "app";

  private static final String BASIC_AUTH_HEADER = "Authorization";

  /**
   * The {@link AppRepository} that is used to look at the available registrations for the current
   * user.
   */
  private final AppRepository repository;

  public BasicAuthFilter(AppRepository repository) {
    this.repository = repository;
  }

  @Override
  public void doFilter(
      ServletRequest request,
      ServletResponse response,
      FilterChain chain
  ) throws IOException, ServletException {
    var req = (HttpServletRequest) request;
    var res = (HttpServletResponse) response;

    // Enforce API keys.
    var cred = req.getHeader(BASIC_AUTH_HEADER);
    if (cred == null || !cred.trim().startsWith("Basic ")) {
      chain.doFilter(req, res);
      return;
    }

    cred = new String(
        Base64.getDecoder().decode(cred.trim().substring("Basic ".length())),
        StandardCharsets.UTF_8
    );

    var split = cred.split(":");
    if (split.length != 2) {
      chain.doFilter(req, res);
      return;
    }

    // Retrieve the app.
    var app = repository.findByNameAndPassword(split[0], split[1]);
    if (app.isEmpty()) {
      chain.doFilter(req, res);
      return;
    }

    // Populate the app for the requests.
    req.setAttribute(APP_KEY, app.get());
    chain.doFilter(req, res);
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    // ignored.
  }

  @Override
  public void destroy() {
    // ignored.
  }
}
