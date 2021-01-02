package ch.heigvd.gamify.domain.endUser;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndUser {

  @EmbeddedId
  private EndUserIdentifier idEndUser;
}
