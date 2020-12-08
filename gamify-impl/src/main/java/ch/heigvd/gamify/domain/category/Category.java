package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import java.io.Serializable;
import javax.persistence.*;

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
}
