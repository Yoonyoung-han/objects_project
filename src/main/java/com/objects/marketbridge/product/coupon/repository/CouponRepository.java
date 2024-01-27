package com.objects.marketbridge.product.coupon.repository;

import com.objects.marketbridge.common.infra.entity.Coupon;

import java.util.List;

public interface CouponRepository {
    Coupon findById(Long id);

    List<Coupon> findAllByIds(List<Long> ids);

    void save(Coupon coupon);

    void saveAll(List<Coupon> coupons);

    List<Coupon> findAll();
}
