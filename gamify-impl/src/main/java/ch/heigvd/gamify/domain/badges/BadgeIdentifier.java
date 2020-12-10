package ch.heigvd.gamify.domain.badges;

import ch.heigvd.gamify.domain.app.App;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
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
public class BadgeIdentifier implements Serializable {
  private String badgeName;

  @ManyToOne
  private App app;
}
