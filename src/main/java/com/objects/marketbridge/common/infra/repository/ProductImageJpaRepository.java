package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.ProductImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageJpaRepository extends JpaRepository<ProductImageEntity, Long> {
}
