package com.objects.marketbridge.product.category;

import com.objects.marketbridge.common.infra.entity.Category;

public interface CategoryRepository {

    Category findById(Long id);

}
