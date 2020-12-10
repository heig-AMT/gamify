package ch.heigvd.gamify.domain.rule;

import ch.heigvd.gamify.domain.category.Category;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
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
   * The category to which the {@link Rule} applies.
   */
  @ManyToOne
  private Category category;
}
