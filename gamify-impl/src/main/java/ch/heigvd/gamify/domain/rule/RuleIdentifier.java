package ch.heigvd.gamify.domain.rule;

import ch.heigvd.gamify.domain.app.App;
import java.io.Serializable;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RuleIdentifier implements Serializable {

  /**
   * The unique name of this {@link Rule}.
   */
  String name;

  /**
   * The {@link App} for which this {@link Rule} is defined,
   */
  String app;
}
