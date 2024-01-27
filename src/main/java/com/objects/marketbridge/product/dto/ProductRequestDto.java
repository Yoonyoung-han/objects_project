package com.objects.marketbridge.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ProductRequestDto {

//    private Long productId;

    @NotNull
    private Long categoryId;
    //    private List<OrderDetail> orderDetails = new ArrayList<>();

    @NotNull
    private Boolean isOwn; // 로켓 true , 오픈 마켓 false
    @NotNull
    private String name;
    @NotNull
    private Long price;
    @NotNull
    private Boolean isSubs;
    @NotNull
    private Long stock;
    @NotNull
    private String thumbImg;

    private List<String> itemImgUrls = new ArrayList<>();
    private List<String> detailImgUrls = new ArrayList<>();

    @NotNull
    private Long discountRate;

    @NotNull
    private List<String> optionNames = new ArrayList<>();


    @Builder
    public ProductRequestDto(Long categoryId, Boolean isOwn, String name, Long price, Boolean isSubs, Long stock, String thumbImg, List<String> itemImgUrls, List<String> detailImgUrls, Long discountRate, List<String> optionNames) {
        this.categoryId = categoryId;
        this.isOwn = isOwn;
        this.name = name;
        this.price = price;
        this.isSubs = isSubs;
        this.stock = stock;
        this.thumbImg = thumbImg;
        this.itemImgUrls = itemImgUrls;
        this.detailImgUrls = detailImgUrls;
        this.discountRate = discountRate;
        this.optionNames = optionNames;
    }
}
