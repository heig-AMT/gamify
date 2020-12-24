package ch.heigvd.gamify.domain.endUser;

import ch.heigvd.gamify.domain.app.App;
import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Embeddable
public class EndUserIdentifier implements Serializable {
  private String userId;
  private App app;
}
