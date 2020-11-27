package ch.heigvd.gamify.api.endpoints;

import ch.heigvd.gamify.api.RegisterApi;
import ch.heigvd.gamify.api.model.Registration;
import ch.heigvd.gamify.api.model.RegistrationSuccess;
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
  public ResponseEntity<RegistrationSuccess> addApp(@Valid Registration registration) {
    var exists = repository.findById(registration.getAppId()).isPresent();
    if (exists) {
      return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
    var token = UUID.randomUUID().toString();
    var entity = RegisteredAppEntity.builder()
        .name(registration.getAppId())
        .password(registration.getPassword())
        .token(token)
        .build();
    repository.save(entity);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(new RegistrationSuccess().token(token));
  }
}
