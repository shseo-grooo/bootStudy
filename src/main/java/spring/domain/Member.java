package spring.domain;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Setter;
import spring.domain.shared.BaseEntity;
import spring.domain.value_type.Address;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
public class Member extends BaseEntity {
    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();
}
