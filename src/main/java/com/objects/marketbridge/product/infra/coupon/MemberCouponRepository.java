package com.objects.marketbridge.product.infra.coupon;

import com.objects.marketbridge.coupon.domain.MemberCoupon;

import java.util.List;

public interface MemberCouponRepository {
    MemberCoupon findById(Long id);

    MemberCoupon findByMemberIdAndCouponId(Long memberId, Long couponId);

    MemberCoupon save(MemberCoupon memberCoupon);

    List<MemberCoupon> saveAll(List<MemberCoupon> memberCoupons);

    List<MemberCoupon> findAll();


}
