package com.objects.marketbridge.order.payment.service.port;


import com.objects.marketbridge.order.payment.domain.Payment;

public interface PaymentRepository {
    void save(Payment payment);
    Payment findById(Long id);

    Payment findByOrderId(Long orderId);

    Payment findByOrderNo(String orderNo);

    void deleteAllInBatch();
}
