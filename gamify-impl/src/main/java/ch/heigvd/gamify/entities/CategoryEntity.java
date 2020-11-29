package ch.heigvd.gamify.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity implements Serializable
{
    @Id
    private String name;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;
}
