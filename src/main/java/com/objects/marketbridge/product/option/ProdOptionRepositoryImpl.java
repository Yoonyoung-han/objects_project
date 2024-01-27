package com.objects.marketbridge.product.option;

import com.objects.marketbridge.common.infra.repository.ProdOptionJpaRepository;
import com.objects.marketbridge.common.infra.entity.ProdOptionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProdOptionRepositoryImpl implements ProdOptionRepository {

    private final ProdOptionJpaRepository prodOptionJpaRepository;

    @Override
    public void save(ProdOptionEntity prodOption) {
        prodOptionJpaRepository.save(prodOption);
    }
}
