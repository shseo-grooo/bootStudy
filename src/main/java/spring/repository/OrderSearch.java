package spring.repository;

import lombok.Getter;
import lombok.Setter;
import spring.domain.custom_enum.OrderStatus;

@Getter
@Setter
public class OrderSearch {
    private String memberName;
    private OrderStatus orderStatus;
}
