package ch.heigvd.gamify.domain.endUserPoints;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndUserPoints {
  @EmbeddedId
  private EndUserPointsIdentifier idEndUserPoints;
  private int points;

  @Transactional
  public void addPoints(int newPoints) {
    this.points += newPoints;
  }
}
