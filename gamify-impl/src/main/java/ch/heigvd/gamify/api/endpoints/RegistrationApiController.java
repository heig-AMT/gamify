package ch.heigvd.gamify.api.endpoints;

import ch.heigvd.gamify.api.RegisterApi;
import ch.heigvd.gamify.api.model.AuthenticationSuccess;
import ch.heigvd.gamify.api.model.Credentials;
import ch.heigvd.gamify.entities.RegisteredAppEntity;
import ch.heigvd.gamify.repositories.RegisteredAppRepository;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

@Controller
public class RegistrationApiController implements RegisterApi {

  private final RegisteredAppRepository repository;

  public RegistrationApiController(RegisteredAppRepository repository) {
    this.repository = repository;
  }

  @Transactional
  @Override
  public ResponseEntity<AuthenticationSuccess> addApp(@Valid Credentials credentials) {
    var exists = repository.findById(credentials.getAppId()).isPresent();
    if (exists) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    var token = UUID.randomUUID().toString();
    var entity = RegisteredAppEntity.builder()
        .name(credentials.getAppId())
        .password(credentials.getPassword())
        .token(token)
        .build();
    repository.save(entity);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new AuthenticationSuccess().token(token));
  }
}
