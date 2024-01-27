package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.order.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryJpaRepository extends JpaRepository<Delivery, Long> {
}
