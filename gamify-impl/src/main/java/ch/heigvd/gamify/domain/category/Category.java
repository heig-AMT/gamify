package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//@IdClass(CategoryIdentifier.class)
public class Category implements Serializable {

  @Id
  private String name;
  private String title;
  private String description;

  //@Id
  @ManyToOne
  private App app;
}
