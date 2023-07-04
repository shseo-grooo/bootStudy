package spring.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spring.domain.custom_enum.DeliveryStatus;
import spring.domain.shared.BaseEntity;
import spring.domain.value_type.Address;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Entity
@Setter @Getter
public class Delivery extends BaseEntity {
    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;
}
