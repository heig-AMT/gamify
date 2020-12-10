package ch.heigvd.gamify.domain.badges;

import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.category.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Badge implements Serializable {
  @Id
  private String name;

  @ManyToOne
  private Category category;

  @ManyToOne
  private App app;

  @NotNull
  private String title, description;

  private int pointsLower, pointsUpper;
}
