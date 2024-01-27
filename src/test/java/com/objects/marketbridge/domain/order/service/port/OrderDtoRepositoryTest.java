package com.objects.marketbridge.domain.order.service.port;

import com.objects.marketbridge.member.repository.MemberRepository;
import com.objects.marketbridge.order.controller.response.OrderCancelReturnListResponse;
import com.objects.marketbridge.order.controller.response.OrderDetailResponse;
import com.objects.marketbridge.order.domain.Order;
import com.objects.marketbridge.order.domain.OrderDetail;
import com.objects.marketbridge.order.service.port.OrderDtoRepository;
import com.objects.marketbridge.order.service.port.OrderRepository;
import com.objects.marketbridge.product.repository.ProductRepository;
import com.objects.marketbridge.common.infra.entity.MemberEntity;
import com.objects.marketbridge.common.infra.entity.ProductEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.objects.marketbridge.order.domain.StatusCodeType.*;
import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@Transactional
@SpringBootTest
class OrderDtoRepositoryTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    OrderDtoRepository orderDtoRepository;
    @Autowired
    EntityManager entityManager;

    @BeforeEach
    public void beforeEach() {

    }

    @Test
    @DisplayName("유저가 반품, 취소한 상품들을 조회할 수 있다.")
    public void findOrdersByMemberId() {
        // given
        MemberEntity member = MemberEntity.builder().build();

        Order order1 = Order.builder()
                .member(member)
                .orderNo("123")
                .build();

        Order order2 = Order.builder()
                .member(member)
                .orderNo("456")
                .build();

        ProductEntity product1 = ProductEntity.builder()
                .productNo("1")
                .name("옷")
                .price(1000L)
                .build();
        ProductEntity product2 = ProductEntity.builder()
                .productNo("2")
                .name("신발")
                .price(2000L)
                .build();
        ProductEntity product3 = ProductEntity.builder()
                .productNo("3")
                .name("바지")
                .price(3000L)
                .build();

        OrderDetail orderDetail1 = OrderDetail.builder()
                .order(order1)
                .product(product1)
                .quantity(1L)
                .orderNo(order1.getOrderNo())
                .statusCode(RETURN_COMPLETED.getCode())
                .build();
        OrderDetail orderDetail2 = OrderDetail.builder()
                .order(order1)
                .product(product2)
                .quantity(2L)
                .orderNo(order1.getOrderNo())
                .statusCode(ORDER_CANCEL.getCode())
                .build();
        OrderDetail orderDetail3 = OrderDetail.builder()
                .order(order2)
                .product(product3)
                .quantity(3L)
                .orderNo(order2.getOrderNo())
                .statusCode(ORDER_CANCEL.getCode())
                .build();
        OrderDetail orderDetail4 = OrderDetail.builder()
                .order(order2)
                .product(product2)
                .quantity(4L)
                .orderNo(order2.getOrderNo())
                .statusCode(DELIVERY_ING.getCode())
                .build();

        order1.addOrderDetail(orderDetail1);
        order1.addOrderDetail(orderDetail2);
        order2.addOrderDetail(orderDetail3);

        productRepository.saveAll(List.of(product1, product2, product3));
        memberRepository.save(member);
        orderRepository.save(order1);
        orderRepository.save(order2);

        // when
        Page<OrderCancelReturnListResponse> orderCancelReturnListResponsePage = orderDtoRepository.findOrdersByMemberId(member.getId(), PageRequest.of(0, 3));
        List<OrderCancelReturnListResponse> content = orderCancelReturnListResponsePage.getContent();
        // then
        assertThat(content).hasSize(2)
                .extracting("orderNo")
                .contains("123", "456");

        List<OrderDetailResponse> orderDetailResponses1 = content.get(0).getOrderDetailResponses();
        List<OrderDetailResponse> orderDetailResponses2 = content.get(1).getOrderDetailResponses();

        assertThat(orderDetailResponses1).hasSize(2)
                .extracting("orderNo", "productId", "productNo", "name", "price", "quantity", "orderStatus")
                .contains(
                        tuple("123", 1L, "1", "옷", 1000L, 1L, RETURN_COMPLETED.getCode()),
                        tuple("123", 2L, "2", "신발", 2000L, 2L, ORDER_CANCEL.getCode())
                );

        assertThat(orderDetailResponses2).hasSize(1)
                .extracting("orderNo", "productId", "productNo", "name", "price", "quantity", "orderStatus")
                .contains(
                        tuple("456", 3L, "3", "바지", 3000L, 3L, ORDER_CANCEL.getCode())
                );

    }

}