package ch.heigvd.gamify.domain.endUser;

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
public class EndUser {

  @EmbeddedId
  private EndUserIdentifier idEndUser;
  private int points;

  @Transactional
  public void addPoints(int newPoints) {
    this.points += newPoints;
  }
}
