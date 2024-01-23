package com.objects.marketbridge.domain.category.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateCategoryResponseDto {

    private Long categoryId;

    public CreateCategoryResponseDto(Long categoryId) {
        this.categoryId = categoryId;
    }
}
