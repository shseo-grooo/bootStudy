package spring.domain;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
public class Album extends Item {
    private String artist;
    private String etc;
}
