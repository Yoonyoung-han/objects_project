package com.objects.marketbridge.product.option;

import com.objects.marketbridge.common.infra.repository.OptionJpaRepository;
import com.objects.marketbridge.common.infra.entity.OptionEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OptionRepositoryImpl implements OptionRepository {

    private final OptionJpaRepository optionJpaRepository;

    @Override
    public void save(OptionEntity option) {
        optionJpaRepository.save(option);
    }

    @Override
    public OptionEntity findById(Long id) {
        return optionJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public OptionEntity findByName(String name) {
        return optionJpaRepository.findByName(name);
    }
}
