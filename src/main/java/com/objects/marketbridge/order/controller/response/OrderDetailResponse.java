package com.objects.marketbridge.order.controller.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
public class OrderDetailResponse {

    private String orderNo;
    private Long productId;
    private String productNo;
    private String name;
    private Long price;
    private Long quantity;
    private String orderStatus;

    @Builder
    @QueryProjection
    public OrderDetailResponse(String orderNo, Long productId, String productNo, String name, Long price, Long quantity, String orderStatus) {
        this.orderNo = orderNo;
        this.productId = productId;
        this.productNo = productNo;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.orderStatus = orderStatus;
    }
}
