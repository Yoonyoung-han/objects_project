package com.objects.marketbridge.order.payment.repository;

import com.objects.marketbridge.common.infra.repository.PaymentJpaRepository;
import com.objects.marketbridge.order.payment.service.port.PaymentRepository;
import com.objects.marketbridge.order.payment.domain.Payment;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryImpl implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;

    @Override
    public void save(Payment payment) {
        paymentJpaRepository.save(payment);
    }

    @Override
    public Payment findById(Long id) {
        return paymentJpaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("엔티티가 존재하지 않습니다."));
    }

    @Override
    public Payment findByOrderId(Long orderId) {
        return paymentJpaRepository.findByOrderId(orderId);
    }

    @Override
    public Payment findByOrderNo(String orderNo) {
        return paymentJpaRepository.findByOrderNo(orderNo);
    }

    @Override
    public void deleteAllInBatch() {
        paymentJpaRepository.deleteAllInBatch();
    }
}
