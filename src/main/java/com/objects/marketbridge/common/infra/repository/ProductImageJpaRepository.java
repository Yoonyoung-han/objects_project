package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageJpaRepository extends JpaRepository<ProductImage, Long> {
}
