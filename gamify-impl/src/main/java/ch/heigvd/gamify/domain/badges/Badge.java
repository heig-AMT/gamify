package ch.heigvd.gamify.domain.badges;

import ch.heigvd.gamify.domain.category.Category;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Badge implements Serializable {
  @EmbeddedId
  private BadgeIdentifier idBadge;

  @ManyToOne
  private Category category;

  @NotNull
  private String title, description;

  private int pointsLower, pointsUpper;
}
