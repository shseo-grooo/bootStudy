package spring.domain;

import jakarta.persistence.*;
import lombok.Setter;
import spring.domain.shared.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
public class Category extends BaseEntity {
    private String name;

    @ManyToMany
    private List<Item> items = new ArrayList<>();


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();
}
