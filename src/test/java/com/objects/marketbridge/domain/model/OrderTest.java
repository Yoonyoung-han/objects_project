package com.objects.marketbridge.domain.model;

import com.objects.marketbridge.order.domain.Order;
import com.objects.marketbridge.order.domain.OrderDetail;
import com.objects.marketbridge.order.service.port.OrderDetailRepository;
import com.objects.marketbridge.order.service.port.OrderRepository;
import com.objects.marketbridge.product.repository.ProductRepository;
import com.objects.marketbridge.common.infra.entity.CouponEntity;
import com.objects.marketbridge.common.infra.entity.MemberCouponEntity;
import com.objects.marketbridge.common.infra.entity.ProductEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
class OrderTest {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private EntityManager em;
    @Test
    @DisplayName("주문 취소시 사용한 유저 쿠폰이 모두 반환되야 한다.")
    public void returnCoupon() {
        // given
        LocalDateTime useDate = LocalDateTime.of(2024, 1, 16, 7, 14);

        Order order = Order.builder()
                .build();

        ProductEntity product1 = ProductEntity.builder()
                .build();
        ProductEntity product2 = ProductEntity.builder()
                .build();

        CouponEntity coupon1 = CouponEntity.builder()
                .product(product1)
                .price(1000L)
                .count(10L)
                .build();
        CouponEntity coupon2 = CouponEntity.builder()
                .product(product2)
                .price(2000L)
                .count(20L)
                .build();

        OrderDetail orderDetail1 = OrderDetail.builder()
                .order(order)
                .coupon(coupon1)
                .product(product1)
                .build();
        OrderDetail orderDetail2 = OrderDetail.builder()
                .order(order)
                .coupon(coupon2)
                .product(product2)
                .build();

        MemberCouponEntity memberCoupon1 = MemberCouponEntity.builder()
                .coupon(coupon1)
                .isUsed(true)
                .usedDate(useDate)
                .build();
        MemberCouponEntity memberCoupon2 = MemberCouponEntity.builder()
                .coupon(coupon2)
                .isUsed(true)
                .usedDate(useDate)
                .build();

        orderRepository.save(order);
        orderDetailRepository.saveAll(List.of(orderDetail1, orderDetail2));
        productRepository.saveAll(List.of(product1, product2));
        order.addOrderDetail(orderDetail1);
        order.addOrderDetail(orderDetail2);
        em.persist(coupon1);
        em.persist(coupon2);
        em.persist(memberCoupon1);
        em.persist(memberCoupon2);
        coupon1.addMemberCoupon(memberCoupon1);
        coupon2.addMemberCoupon(memberCoupon2);

        Order findOrder = orderRepository.findById(order.getId()).get();

        // when
        findOrder.returnCoupon();

        // then
        assertThat(coupon1.getCount()).isEqualTo(11L);
        assertThat(coupon2.getCount()).isEqualTo(21L);
        assertThat(memberCoupon1.getUsedDate()).isNull();
        assertThat(memberCoupon2.getUsedDate()).isNull();
        assertThat(memberCoupon1.getIsUsed()).isFalse();
        assertThat(memberCoupon2.getIsUsed()).isFalse();
    }

}