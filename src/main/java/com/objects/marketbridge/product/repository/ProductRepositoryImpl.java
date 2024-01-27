package com.objects.marketbridge.product.repository;

import com.objects.marketbridge.common.infra.repository.ProductJpaRepository;
import com.objects.marketbridge.common.infra.entity.ProductEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public List<ProductEntity> findAll() {
        return productJpaRepository.findAll();
    }

    @Override
    public ProductEntity findById(Long id) {
        return productJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<ProductEntity> findAllById(List<Long> ids) {
        return productJpaRepository.findAllById(ids);
    }

    @Override
    public List<ProductEntity> findByName(String name) {
        return productJpaRepository.findByName(name);
    }

    @Override
    public void deleteAllInBatch() {
        productJpaRepository.deleteAllInBatch();
    }

    @Override
    public void save(ProductEntity product) {
        productJpaRepository.save(product);
    }

    @Override
    public void saveAll(List<ProductEntity> products) {
        productJpaRepository.saveAll(products);
    }
}
