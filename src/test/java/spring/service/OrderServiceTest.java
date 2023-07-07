package spring.service;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import spring.domain.Member;
import spring.domain.Order;
import spring.domain.custom_enum.OrderStatus;
import spring.domain.item.Book;
import spring.domain.value_type.Address;
import spring.exception.NotEnoughStockException.NotEnoughStockException;
import spring.repository.OrderRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    void 상품주문() throws Exception {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "경기", "123"));
        em.persist(member);

        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

        Order order = orderRepository.findOne(orderId);

        assertEquals(order.getStatus(), OrderStatus.ORDER);
        assertEquals(1, order.getOrderItems().size());
        assertEquals(orderCount*book.getPrice(), order.getTotalPrice());
        assertEquals(8, book.getStockQuantity());
    }

    @Test
    void 주문취소() throws Exception {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "경기", "123"));
        em.persist(member);

        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order order = orderRepository.findOne(orderId);

        orderService.cancelOrder(order.getId());

        assertEquals(order.getStatus(), OrderStatus.CANCEL);
        assertEquals(10, book.getStockQuantity());
    }

    @Test
    void 상품주문_재고수량초과() throws Exception {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "경기", "123"));
        em.persist(member);

        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);

        int orderCount = 12;
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(), orderCount));
    }
}