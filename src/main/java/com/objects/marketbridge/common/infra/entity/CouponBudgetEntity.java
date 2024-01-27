package com.objects.marketbridge.common.infra.entity;

import com.objects.marketbridge.product.seller.domain.Seller;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CouponBudgetEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_budget_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    private Seller seller;

    private Long balance;

    private Long outgoing;

    private Long incoming;

    @Builder
    public CouponBudgetEntity(Seller seller, Long balance, Long outgoing, Long incoming) {
        this.seller = seller;
        this.balance = balance;
        this.outgoing = outgoing;
        this.incoming = incoming;
    }
}
