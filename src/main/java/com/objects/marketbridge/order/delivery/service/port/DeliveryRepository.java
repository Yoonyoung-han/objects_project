package com.objects.marketbridge.order.delivery.service.port;

import com.objects.marketbridge.order.delivery.domain.Delivery;

import java.util.List;

public interface DeliveryRepository {
    Delivery findById(Long id);

    Delivery save(Delivery delivery);

    List<Delivery> saveAll(List<Delivery> deliveries);

    List<Delivery> findAll();

    List<Delivery> findAllInIds(List<Long> ids);
}
