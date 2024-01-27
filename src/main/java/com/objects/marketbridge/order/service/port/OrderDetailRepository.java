package com.objects.marketbridge.order.service.port;

import com.objects.marketbridge.order.domain.OrderDetail;
import com.objects.marketbridge.common.infra.entity.ProductEntity;

import java.util.List;

public interface OrderDetailRepository  {

    int changeAllType(Long orderId, String type);

    List<OrderDetail> saveAll(List<OrderDetail> orderDetail);

    void addReason(Long orderId, String reason);

    void deleteAllInBatch();

    void save(OrderDetail orderDetail);

    OrderDetail findById(Long id);

    List<OrderDetail> findByProductId(Long id);

    List<OrderDetail> findAll();

    List<OrderDetail> findByOrderNo(String orderNo);

    List<OrderDetail> findByOrder_IdAndProductIn(Long orderId, List<ProductEntity> products);

    List<OrderDetail> findByOrderNoAndProduct_IdIn(String orderNo, List<Long> productIds);

//    OrderDetail findByStockIdAndOrderId(Long stockId, Long orderId);
}
