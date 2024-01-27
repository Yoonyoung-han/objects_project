package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberCouponJpaRepository extends JpaRepository<MemberCoupon, Long> {

    @Query("SELECT mc FROM MemberCoupon mc WHERE mc.member.id = :memberId AND mc.coupon.id = :couponId")
    MemberCoupon findByMember_IdAndCoupon_Id(Long memberId, Long couponId);
}
