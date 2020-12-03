package ch.heigvd.gamify.ui.api.endpoints;

import ch.heigvd.gamify.api.AccountApi;
import ch.heigvd.gamify.api.model.AuthenticationSuccess;
import ch.heigvd.gamify.api.model.Registration;
import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.app.AppRepository;
import ch.heigvd.gamify.ui.api.filters.BasicAuthFilter;
import java.util.Optional;
import java.util.UUID;
import javax.servlet.ServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

@RestController
public class AccountController implements AccountApi {

  @Autowired
  AppRepository repository;

  @Autowired
  ServletRequest request;

  @Override
  public Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  @Transactional
  @Override
  public ResponseEntity<AuthenticationSuccess> registerAccount(@Valid Registration registration) {
    var exists = repository.findById(registration.getUsername()).isPresent();
    if (exists) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    var token = UUID.randomUUID().toString();
    var entity = App.builder()
        .name(registration.getUsername())
        .password(registration.getPassword())
        .token(token)
        .build();
    repository.save(entity);

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new AuthenticationSuccess().token(token));
  }

  @Override
  public ResponseEntity<AuthenticationSuccess> login() {
    var app = (App) request.getAttribute(BasicAuthFilter.APP_KEY);

    if (app == null) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(new AuthenticationSuccess().token(app.getToken()));
  }
}
