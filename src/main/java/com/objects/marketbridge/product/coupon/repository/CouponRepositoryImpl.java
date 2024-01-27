package com.objects.marketbridge.product.coupon.repository;

import com.objects.marketbridge.common.infra.repository.CouponJpaRepository;
import com.objects.marketbridge.common.infra.entity.CouponEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CouponRepositoryImpl implements CouponRepository {

    private final CouponJpaRepository couponJpaRepository;

    @Override
    public CouponEntity findById(Long id) {
        return couponJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public List<CouponEntity> findAllByIds(List<Long> ids) {
        return couponJpaRepository.findAllById(ids);
    }
    @Override
    public void save(CouponEntity coupon) {
        couponJpaRepository.save(coupon);
    }

    @Override
    public void saveAll(List<CouponEntity> coupons) {
        couponJpaRepository.saveAll(coupons);
    }

    @Override
    public List<CouponEntity> findAll() {
        return couponJpaRepository.findAll();
    }
}
