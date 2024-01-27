package com.objects.marketbridge.product.coupon.repository;

import com.objects.marketbridge.common.infra.entity.CouponEntity;

import java.util.List;

public interface CouponRepository {
    CouponEntity findById(Long id);

    List<CouponEntity> findAllByIds(List<Long> ids);

    void save(CouponEntity coupon);

    void saveAll(List<CouponEntity> coupons);

    List<CouponEntity> findAll();
}
