package com.objects.marketbridge.product.coupon.repository;

import com.objects.marketbridge.common.infra.entity.MemberCouponEntity;

import java.util.List;

public interface MemberCouponRepository {
    MemberCouponEntity findById(Long id);

    MemberCouponEntity findByMember_IdAndCoupon_Id(Long MemberId, Long CouponId);

    MemberCouponEntity save(MemberCouponEntity memberCoupon);

    List<MemberCouponEntity> saveAll(List<MemberCouponEntity> memberCoupons);

    List<MemberCouponEntity> findAll();


}
