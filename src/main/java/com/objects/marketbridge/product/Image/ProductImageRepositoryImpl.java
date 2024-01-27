package com.objects.marketbridge.product.Image;

import com.objects.marketbridge.common.infra.repository.ProductImageJpaRepository;
import com.objects.marketbridge.common.infra.entity.ProductImageEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductImageRepositoryImpl implements ProductImageRepository{

    private final ProductImageJpaRepository productImageJpaRepository;

    @Override
    public void save(ProductImageEntity productImage) {
        productImageJpaRepository.save(productImage);
    }

}
