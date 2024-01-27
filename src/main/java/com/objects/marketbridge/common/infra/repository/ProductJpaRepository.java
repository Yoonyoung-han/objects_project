package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<Product, Long> {
    List<Product> findByName(String name);
}
