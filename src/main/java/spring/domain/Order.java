package spring.domain;

import jakarta.persistence.*;
import lombok.Setter;
import spring.domain.custom_enum.OrderStatus;
import spring.domain.shared.BaseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> OrderItems = new ArrayList<>();


    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
