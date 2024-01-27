package com.objects.marketbridge.product.category;

import com.objects.marketbridge.common.infra.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<CategoryEntity, Long> {

}
