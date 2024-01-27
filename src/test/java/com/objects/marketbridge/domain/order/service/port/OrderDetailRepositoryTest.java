package com.objects.marketbridge.domain.order.service.port;

import com.objects.marketbridge.order.domain.Order;
import com.objects.marketbridge.order.domain.OrderDetail;
import com.objects.marketbridge.order.service.port.OrderDetailRepository;
import com.objects.marketbridge.order.service.port.OrderRepository;
import com.objects.marketbridge.product.repository.ProductRepository;
import com.objects.marketbridge.common.infra.entity.ProductEntity;
import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.objects.marketbridge.order.domain.StatusCodeType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class OrderDetailRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired EntityManager em;


    @Test
    @DisplayName("특정 주문에 해당하는 주문 상세의 상태 코드를 한번에 바꾼다.")
    public void changeAllType() {
        // given
        String givenCodeType = PAYMENT_COMPLETED.getCode();
        String changeCodeType = ORDER_CANCEL.getCode();

        OrderDetail orderDetail1 = createOrderDetail_type(givenCodeType);
        OrderDetail orderDetail2 = createOrderDetail_type(givenCodeType);
        OrderDetail orderDetail3 = createOrderDetail_type(givenCodeType);

        Order order = Order.builder().build();
        order.addOrderDetail(orderDetail1);
        order.addOrderDetail(orderDetail2);
        order.addOrderDetail(orderDetail3);

        Order savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();

        // when
        int result = orderDetailRepository.changeAllType(orderId, changeCodeType);

        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    @DisplayName("주문 상세 리스트를 모두 저장한다.")
    public void saveAll() {
        // given
        String givenType = PAYMENT_COMPLETED.getCode();
        OrderDetail orderDetail1 = createOrderDetail_type(givenType);
        OrderDetail orderDetail2 = createOrderDetail_type(givenType);
        OrderDetail orderDetail3 = createOrderDetail_type(givenType);
        List<OrderDetail> orderDetails = List.of(orderDetail1, orderDetail2, orderDetail3);

        // when
        List<OrderDetail> savedOrderDetails = orderDetailRepository.saveAll(orderDetails);

        // then
        assertThat(savedOrderDetails.size()).isEqualTo(3);
    }

    @Test
    @DisplayName("주문 상세에 이유 넣기")
    public void addReason() {
        // given
        String reason = "상품이 맘에들지 않아요";
        OrderDetail orderDetail1 = OrderDetail.builder().build();
        OrderDetail orderDetail2 = OrderDetail.builder().build();
        OrderDetail orderDetail3 = OrderDetail.builder().build();

        Order order = Order.builder().build();
        order.addOrderDetail(orderDetail1);
        order.addOrderDetail(orderDetail2);
        order.addOrderDetail(orderDetail3);

        Order savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();

        // when
        orderDetailRepository.addReason(orderId, reason);

        // then
        String savedReason = getReason(orderId);
        assertThat(reason).isEqualTo(savedReason);
    }

    @Test
    @DisplayName("주문 ID와 상품 리스트가 주어지면 주문 상세 리스트를 조회할 수 있다.")
    public void findByProdOrder_IdAndProductIn() {
        // given
        ProductEntity product1 = ProductEntity.builder().name("옷").build();
        ProductEntity product2 = ProductEntity.builder().name("바지").build();
        ProductEntity product3 = ProductEntity.builder().name("신발").build();

        OrderDetail orderDetail1 = OrderDetail.builder().product(product1).build();
        OrderDetail orderDetail2 = OrderDetail.builder().product(product2).build();
        OrderDetail orderDetail3 = OrderDetail.builder().product(product3).build();

        Order order = Order.builder().build();
        order.addOrderDetail(orderDetail1);
        order.addOrderDetail(orderDetail2);
        order.addOrderDetail(orderDetail3);

        List<ProductEntity> products = List.of(product1, product2, product3);
        productRepository.saveAll(products);
        orderRepository.save(order);

        // when
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrder_IdAndProductIn(order.getId(), products);

        // then
        Assertions.assertThat(orderDetails).hasSize(3)
                .extracting("product", "order")
                .contains(
                        tuple(product1, order),
                        tuple(product2, order),
                        tuple(product3, order)
                );
    }

    @Test
    @DisplayName("주문 번호와 상품 ID 리스트가 주어지면 상품 상세 리스트를 조회할 수 있다.")
    public void findByOrderNoAndProduct_IdIn() {
        // given
        ProductEntity product1 = ProductEntity.builder()
                .price(1000L)
                .thumbImg("썸네일1")
                .name("옷")
                .build();
        ProductEntity product2 = ProductEntity.builder()
                .name("바지")
                .price(2000L)
                .thumbImg("썸네일2")
                .build();
        ProductEntity product3 = ProductEntity.builder()
                .name("신발")
                .price(3000L)
                .thumbImg("썸네일3")
                .build();

        OrderDetail orderDetail1 = OrderDetail.builder()
                .product(product1)
                .quantity(2L)
                .price(product1.getPrice() * 2L)
                .orderNo("123")
                .statusCode(ORDER_CANCEL.getCode())
                .reason("빵빵이")
                .build();
        OrderDetail orderDetail2 = OrderDetail.builder()
                .product(product2)
                .quantity(3L)
                .price(product2.getPrice() * 3L)
                .orderNo("123")
                .statusCode(ORDER_CANCEL.getCode())
                .reason("옥지얌")
                .build();
        OrderDetail orderDetail3 = OrderDetail.builder()
                .product(product3)
                .quantity(4L)
                .price(product3.getPrice() * 4L)
                .orderNo("123")
                .statusCode(RETURN_COMPLETED.getCode())
                .reason("멍청이")
                .build();

        Order order = Order.builder()
                .orderNo("123")
                .build();
        order.addOrderDetail(orderDetail1);
        order.addOrderDetail(orderDetail2);
        order.addOrderDetail(orderDetail3);

        List<ProductEntity> products = List.of(product1, product2, product3);
        productRepository.saveAll(products);
        orderRepository.save(order);
        List<Long> productIds = products.stream().map(ProductEntity::getId).toList();

        // when
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderNoAndProduct_IdIn(order.getOrderNo(), productIds);

        // then
        assertThat(orderDetails).hasSize(3)
                .extracting("orderNo", "product", "quantity", "price", "statusCode", "reason")
                .contains(
                        tuple("123", product1, 2L, 2000L, ORDER_CANCEL.getCode(), "빵빵이"),
                        tuple("123", product2, 3L, 6000L, ORDER_CANCEL.getCode(), "옥지얌"),
                        tuple("123", product3, 4L, 12000L, RETURN_COMPLETED.getCode(), "멍청이")
                );
    }


    private String getReason(Long orderId) {
        List<OrderDetail> orderDetails = orderRepository.findById(orderId).get().getOrderDetails();
        return orderDetails.get(0).getReason();
    }

    private OrderDetail createOrderDetail_type(String code) {
        return OrderDetail.builder()
                .statusCode(code)
                .build();
    }


}