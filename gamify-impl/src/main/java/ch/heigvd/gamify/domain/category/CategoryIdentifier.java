package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
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
