package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.badges.Badge;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {

  @EmbeddedId
  private CategoryIdentifier idCategory;
  private String title;
  private String description;

  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  private List<Badge> badges;
}
