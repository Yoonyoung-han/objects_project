package com.objects.marketbridge.common.infra.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberCouponEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_coupon_id")
    private Long id;

    // TODO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity member;
    // TODO
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private CouponEntity coupon;

    private Boolean isUsed;

    private LocalDateTime usedDate;

    private LocalDateTime endDate;

    @Builder
    private MemberCouponEntity(MemberEntity member, CouponEntity coupon, Boolean isUsed, LocalDateTime usedDate, LocalDateTime endDate) {
        this.member = member;
        this.coupon = coupon;
        this.isUsed = isUsed;
        this.usedDate = usedDate;
        this.endDate = endDate;
    }

    public void returnCoupon() {
        isUsed = !isUsed;
        usedDate = null;
    }

    public void setCoupon(CouponEntity coupon) {
        this.coupon = coupon;
    }

    public void applyCouponUsage(Boolean isUsed, LocalDateTime usedDate) {
        this.isUsed = isUsed;
        this.usedDate = usedDate;
    }
}
