package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;

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
