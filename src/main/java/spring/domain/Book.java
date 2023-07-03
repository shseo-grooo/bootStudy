package spring.domain;

import jakarta.persistence.Entity;
import lombok.Setter;

@Entity
@Setter
public class Book extends Item  {
    private String author;
    private String isbn;
}
