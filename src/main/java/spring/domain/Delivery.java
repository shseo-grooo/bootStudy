package spring.domain;

import jakarta.persistence.*;
import lombok.Setter;
import spring.domain.custom_enum.DeliveryStatus;
import spring.domain.shared.BaseEntity;
import spring.domain.value_type.Address;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
public class Delivery extends BaseEntity {
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
