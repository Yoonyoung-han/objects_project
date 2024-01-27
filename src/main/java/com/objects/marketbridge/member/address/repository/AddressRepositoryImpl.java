package com.objects.marketbridge.member.address.repository;

import com.objects.marketbridge.common.infra.entity.AddressEntity;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressJpaRepository addressJpaRepository;

    @Override
    public AddressEntity findById(Long id) {
        return addressJpaRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    @Override
    public void save(AddressEntity addressEntity) {
        addressJpaRepository.save(addressEntity);
    }

    @Override
    public void saveAll(List<AddressEntity> addressEntities) {
        addressJpaRepository.saveAll(addressEntities);
    }

    @Override
    public List<AddressEntity> findByMemberId(Long memberId) {
        return addressJpaRepository.findByMemberId(memberId);
    }

    @Override
    public void deleteAllInBatch(){
        addressJpaRepository.deleteAllInBatch();
    }
}
