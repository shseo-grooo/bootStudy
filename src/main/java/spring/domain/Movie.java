package spring.domain;

import jakarta.persistence.Entity;
import lombok.Setter;

@Entity
@Setter
public class Movie extends Item  {
    private String director;
    private String actor;
}
