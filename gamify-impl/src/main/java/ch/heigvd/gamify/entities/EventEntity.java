package ch.heigvd.gamify.entities;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @ManyToOne
  private RegisteredAppEntity app;

  @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE")
  private OffsetDateTime timestamp;
  private String type;

  @Column(name = "userId") // PostgreSQL reserves the "user" keyword
  private String user;
}
