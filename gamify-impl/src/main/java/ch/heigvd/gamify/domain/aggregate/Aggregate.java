package ch.heigvd.gamify.domain.aggregate;

import ch.heigvd.gamify.domain.category.CategoryIdentifier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

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
