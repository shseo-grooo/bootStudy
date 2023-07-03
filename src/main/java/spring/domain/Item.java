package spring.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Setter;
import spring.domain.shared.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
public class Item extends BaseEntity {
    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();
}
