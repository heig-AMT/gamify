package ch.heigvd.gamify.domain.endUser;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndUser {

  @Id
  private String userId;
  @Getter
  private int points;

  public void addPoints(int newPoints) {
    this.points += newPoints;
  }
}
