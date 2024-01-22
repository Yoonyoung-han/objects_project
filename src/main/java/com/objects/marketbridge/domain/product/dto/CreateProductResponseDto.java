package com.objects.marketbridge.domain.product.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateProductResponseDto {

    private Long productId;

    public CreateProductResponseDto(Long productId) {
        this.productId = productId;
    }

}
