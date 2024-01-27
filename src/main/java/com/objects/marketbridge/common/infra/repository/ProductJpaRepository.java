package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByName(String name);
}
