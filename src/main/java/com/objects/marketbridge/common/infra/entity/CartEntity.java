package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CartEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    private Boolean isSubs;

    private Long quantity;

    @Builder

    public CartEntity(MemberEntity memberId, ProductEntity product, Boolean isSubs, Long quantity) {
        this.memberId = memberId;
        this.product = product;
        this.isSubs = isSubs;
        this.quantity = quantity;
    }

    //    private Cart(Product product, Member memberId, boolean isSubs, Long quantity) {
//        this.product = product;
//        this.memberId = memberId;
//        this.isSubs = isSubs;
//        this.quantity = quantity;
//    }
}