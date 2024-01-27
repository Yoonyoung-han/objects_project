package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponJpaRepository extends JpaRepository<CouponEntity, Long> {
    List<CouponEntity> findByIdIn(List<Long> ids);
}
