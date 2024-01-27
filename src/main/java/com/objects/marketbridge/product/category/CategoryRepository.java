package com.objects.marketbridge.product.category;

import com.objects.marketbridge.common.infra.entity.CategoryEntity;

public interface CategoryRepository {

    CategoryEntity findById(Long id);

}
