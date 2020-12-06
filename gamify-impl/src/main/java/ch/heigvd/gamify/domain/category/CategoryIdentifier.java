package ch.heigvd.gamify.domain.category;

import ch.heigvd.gamify.domain.app.App;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryIdentifier implements Serializable
{
    private String name;
    private App app;
}
