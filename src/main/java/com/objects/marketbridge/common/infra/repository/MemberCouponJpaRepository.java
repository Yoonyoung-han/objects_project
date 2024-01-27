package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.MemberCouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberCouponJpaRepository extends JpaRepository<MemberCouponEntity, Long> {

    @Query("SELECT mc FROM MemberCouponEntity mc WHERE mc.member.id = :memberId AND mc.coupon.id = :couponId")
    MemberCouponEntity findByMember_IdAndCoupon_Id(Long memberId, Long couponId);
}
