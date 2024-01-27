package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByIdIn(List<Long> ids);
}
