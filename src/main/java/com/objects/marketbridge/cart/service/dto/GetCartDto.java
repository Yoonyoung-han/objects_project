package com.objects.marketbridge.cart.service.dto;

import com.objects.marketbridge.cart.domain.Cart;
import com.objects.marketbridge.coupon.domain.Coupon;
import com.objects.marketbridge.product.domain.ProdOption;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@Getter
@NoArgsConstructor
public class GetCartDto {
    private Long productId;
    private String productNo;
    private String productName;
    private Long productPrice;
    private Long quantity;
    private Long discountRate;
    private String thumbImageUrl;
    private Boolean isOwn;
    private Boolean isSubs;
    private Long stock;
    private Long deliveryFee;
    private String deliveredDate; // yyyy.MM.dd
    private List<String> optionNames;
    private List<CouponDto> availableCoupons;

    @Builder
    private GetCartDto(Long productId, String productNo, String productName, Long productPrice, Long quantity, Long discountRate, String thumbImageUrl, Boolean isOwn, Boolean isSubs, Long stock, Long deliveryFee, String deliveredDate, List<String> optionNames, List<CouponDto> availableCoupons) {
        this.productId = productId;
        this.productNo = productNo;
        this.productName = productName;
        this.productPrice = productPrice;
        this.quantity = quantity;
        this.discountRate = discountRate;
        this.thumbImageUrl = thumbImageUrl;
        this.isOwn = isOwn;
        this.isSubs = isSubs;
        this.stock = stock;
        this.deliveryFee = deliveryFee;
        this.deliveredDate = deliveredDate;
        this.optionNames = optionNames;
        this.availableCoupons = availableCoupons;
    }

    public static GetCartDto of(Cart cart) {
        List<ProdOption> prodOptions = cart.getProduct().getProdOptions();
        List<Coupon> coupons = cart.getProduct().getAvailableCoupons();

        return GetCartDto.builder()
                .productId(cart.getProduct().getId())
                .productNo(cart.getProduct().getProductNo())
                .productName(cart.getProduct().getName())
                .productPrice(cart.getProduct().getPrice())
                .quantity(cart.getQuantity())
                .discountRate(cart.getProduct().getDiscountRate())
                .thumbImageUrl(cart.getProduct().getThumbImg())
                .isOwn(cart.getProduct().getIsOwn())
                .isSubs(cart.getProduct().getIsSubs())
                .stock(cart.getProduct().getStock())
                .deliveryFee(0L)
                .deliveredDate("deliveredDate")
                .optionNames(!prodOptions.isEmpty() ?
                        prodOptions.stream().map(po -> po.getOption().getName()).collect(toList()) : null)
                .availableCoupons(!coupons.isEmpty() ?
                        coupons.stream().map(CouponDto::of).collect(toList()) : null)
                .build();
    }

    @Getter
    @NoArgsConstructor
    public static class CouponDto {
        private Long couponId;
        private String name;
        private Long price;
        private String endDate;
        private Long minimumPrice;

        @Builder
        private CouponDto(Long couponId, String name, Long price, String endDate, Long minimumPrice) {
            this.couponId = couponId;
            this.name = name;
            this.price = price;
            this.endDate = endDate;
            this.minimumPrice = minimumPrice;
        }

        public static CouponDto of(Coupon coupon) {
            return CouponDto.builder()
                    .couponId(coupon.getId())
                    .name(coupon.getName())
                    .price(coupon.getPrice())
                    .endDate(coupon.getEndDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .minimumPrice(coupon.getMinimumPrice())
                    .build();
        }
    }
}
