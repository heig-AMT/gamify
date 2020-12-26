package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import ch.heigvd.gamify.domain.badges.Badge;
import ch.heigvd.gamify.domain.endUserPoints.EndUserPoints;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class CategoryIdentifier implements Serializable {

  private String name;

  @ManyToOne
  private App app;
}
