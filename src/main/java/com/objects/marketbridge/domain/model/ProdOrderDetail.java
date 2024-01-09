package com.objects.marketbridge.domain.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProdOrderDetail extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "prod_order_detail_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_order_id")
    private ProdOrder prodOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private String statusCode;

    private Long price;

    @Builder
    private ProdOrderDetail(ProdOrder prodOrder, Product product, String statusCode, Long price) {
        this.prodOrder = prodOrder;
        this.product = product;
        this.statusCode = statusCode;
        this.price = price;
    }
}
