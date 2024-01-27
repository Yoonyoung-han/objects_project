package com.objects.marketbridge.product.repository;

import com.objects.marketbridge.common.infra.entity.ProductEntity;

import java.util.List;

public interface ProductRepository {
    ProductEntity findById(Long id);
    List<ProductEntity> findAllById(List<Long> ids);

    List<ProductEntity> findByName(String name);

    void deleteAllInBatch();
    void save(ProductEntity product);

    List<ProductEntity> findAll();

    void saveAll(List<ProductEntity> products);

}
