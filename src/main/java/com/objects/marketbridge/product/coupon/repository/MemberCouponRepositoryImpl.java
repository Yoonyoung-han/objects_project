package com.objects.marketbridge.product.coupon.repository;

import com.objects.marketbridge.common.infra.repository.MemberCouponJpaRepository;
import com.objects.marketbridge.common.infra.entity.MemberCouponEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberCouponRepositoryImpl implements MemberCouponRepository {

    private final MemberCouponJpaRepository memberCouponJpaRepository;

    @Override
    public MemberCouponEntity findById(Long id) {
        return memberCouponJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public MemberCouponEntity findByMember_IdAndCoupon_Id(Long memberId, Long couponId) {
        return memberCouponJpaRepository.findByMember_IdAndCoupon_Id(memberId, couponId);
    }

    @Override
    public MemberCouponEntity save(MemberCouponEntity memberCoupon) {
        return memberCouponJpaRepository.save(memberCoupon);
    }

    @Override
    public List<MemberCouponEntity> saveAll(List<MemberCouponEntity> memberCoupons) {
        return memberCouponJpaRepository.saveAll(memberCoupons);
    }

    @Override
    public List<MemberCouponEntity> findAll() {
        return memberCouponJpaRepository.findAll();
    }
}
