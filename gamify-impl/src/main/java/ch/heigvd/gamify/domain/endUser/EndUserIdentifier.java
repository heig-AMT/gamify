package ch.heigvd.gamify.domain.endUser;

import ch.heigvd.gamify.domain.app.App;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.ManyToMany;
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
public class EndUserIdentifier implements Serializable {
  private String userId;

  @ManyToOne
  private App app;
}
