package spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.Delivery;
import spring.domain.Member;
import spring.domain.Order;
import spring.domain.OrderItem;
import spring.domain.item.Item;
import spring.repository.ItemRepository;
import spring.repository.MemberRepository;
import spring.repository.OrderRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    final private OrderRepository orderRepository;
    final private MemberRepository memberRepository;
    final private ItemRepository itemRepository;

    public Long  order(Long memberId, Long itemId, int count) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }

//    public List<Order> search() {}
}
