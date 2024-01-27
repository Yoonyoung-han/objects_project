package com.objects.marketbridge.member.address.repository;

import com.objects.marketbridge.common.infra.entity.AddressEntity;

import java.util.List;

public interface AddressRepository {

    AddressEntity findById(Long id);

    List<AddressEntity> findByMemberId(Long memberId);

    void save(AddressEntity addressEntity);

    void saveAll(List<AddressEntity> addressEntities);

    void deleteAllInBatch();
}
