package com.objects.marketbridge.order.delivery.domain;

import com.objects.marketbridge.common.infra.entity.AddressEntity;
import com.objects.marketbridge.order.domain.OrderDetail;
import com.objects.marketbridge.common.infra.entity.BaseEntity;
import com.objects.marketbridge.product.seller.domain.Seller;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    private String deliveryType; // NORMAL, EXCHANGE, RETURN // column이름이랑 다름

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_detail_id")
    private OrderDetail orderDetail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private AddressEntity addressEntity;

    private String carrier;

    private String trackingNo;

    private String statusCode;

    private LocalDateTime shipDate;

    private LocalDateTime deliveredDate;

    @Builder
    private Delivery(String deliveryType, OrderDetail orderDetail, Seller seller, AddressEntity addressEntity, String carrier, String trackingNo, String statusCode, LocalDateTime shipDate, LocalDateTime deliveredDate) {
        this.deliveryType = deliveryType;
        this.orderDetail = orderDetail;
        this.seller = seller;
        this.addressEntity = addressEntity;
        this.carrier = carrier;
        this.trackingNo = trackingNo;
        this.statusCode = statusCode;
        this.shipDate = shipDate;
        this.deliveredDate = deliveredDate;
    }
}
