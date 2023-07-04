package spring.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spring.domain.Category;
import spring.domain.shared.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item extends BaseEntity {
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
