package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.order.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentJpaRepository extends JpaRepository<Payment, Long> {
    Payment findByOrderId(Long orderId);
    Payment findByOrderNo(String orderNo);
}
