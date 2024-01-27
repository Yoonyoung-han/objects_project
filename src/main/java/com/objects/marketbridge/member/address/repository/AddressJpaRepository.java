package com.objects.marketbridge.member.address.repository;

import com.objects.marketbridge.common.infra.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressJpaRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findByMemberId(Long memberId);
}
