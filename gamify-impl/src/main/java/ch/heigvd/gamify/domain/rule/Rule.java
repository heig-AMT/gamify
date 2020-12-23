package ch.heigvd.gamify.domain.rule;

import ch.heigvd.gamify.domain.category.Category;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * A {@link Rule} defines how we map a certain event type to actual points. The total of points in a
 * certain category will return the ranking of an end user.
 */
@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Rule implements Serializable {

  /**
   * The type of the events to which this {@link Rule} applies.
   */
  String eventType;

  /**
   * How many points are attributed for the event to the category.
   */
  @Getter
  int points;

  /**
   * The identifier (including the category) for this {@link Rule}.
   */
  @EmbeddedId
  private RuleIdentifier id;

  /**
   * The {@link Category} that is associated with this {@link Rule}.
   */
  @ManyToOne
  Category category;
}
