package ch.heigvd.gamify.domain.endUserPoints;

import ch.heigvd.gamify.domain.category.Category;
import ch.heigvd.gamify.domain.endUser.EndUser;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
public class EndUserPointsIdentifier implements Serializable {
  @ManyToOne
  @PrimaryKeyJoinColumn
  private Category idxCategory;
  @ManyToOne
  @PrimaryKeyJoinColumn
  private EndUser idxUser;
}
