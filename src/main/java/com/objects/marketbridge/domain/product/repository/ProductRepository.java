package com.objects.marketbridge.domain.product.repository;

import com.objects.marketbridge.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    Product findById(Long id);
    List<Product> findAllById(List<Long> ids);

    List<Product> findByName(String name);

    void save(Product product);

    List<Product> findAll();

    void saveAll(List<Product> products);

    void delete(Product product);

    void deleteById(Long id);

    void deleteAllInBatch();

}
