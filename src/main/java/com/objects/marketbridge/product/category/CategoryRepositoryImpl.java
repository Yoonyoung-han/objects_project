package com.objects.marketbridge.product.category;

import com.objects.marketbridge.common.infra.entity.CategoryEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository{

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public CategoryEntity findById(Long id) {
        return categoryJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
