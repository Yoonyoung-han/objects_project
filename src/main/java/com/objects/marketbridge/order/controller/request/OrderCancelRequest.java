package com.objects.marketbridge.order.controller.request;

import com.objects.marketbridge.order.dto.OrderCancelServiceDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderCancelRequest {

    @NotNull
    private Long orderId;

    @NotNull
    private String cancelReason;


    @Builder
    public OrderCancelRequest(Long orderId, String cancelReason) {
        this.orderId = orderId;
        this.cancelReason = cancelReason;
    }

    public OrderCancelServiceDto toServiceRequest() {
        return OrderCancelServiceDto.builder()
                .orderId(orderId)
                .cancelReason(cancelReason)
                .build();
    }
}
