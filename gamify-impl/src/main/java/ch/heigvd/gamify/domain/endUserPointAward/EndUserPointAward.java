package ch.heigvd.gamify.domain.endUserPointAward;

import ch.heigvd.gamify.domain.category.Category;
import ch.heigvd.gamify.domain.endUser.EndUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndUserPointAward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Category idxCategory;
    @ManyToOne
    private EndUser idxEndUser;

    private int points;
}
