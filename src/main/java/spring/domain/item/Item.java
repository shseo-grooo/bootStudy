package spring.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spring.domain.Category;
import spring.domain.shared.BaseEntity;
import spring.exception.NotEnoughStockException.NotEnoughStockException;

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

    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new NotEnoughStockException("");
        }
        this.stockQuantity -= quantity;
    }
}
