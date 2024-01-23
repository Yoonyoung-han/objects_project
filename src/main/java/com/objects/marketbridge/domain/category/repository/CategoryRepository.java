package com.objects.marketbridge.domain.category.repository;

import com.objects.marketbridge.domain.model.Category;

public interface CategoryRepository {

    Category findById(Long id);

    Boolean existsByName(String name);

    void save(Category category);

    Category findByName(String name);

    Boolean existsByNameAndLevel(String name, Long level);

    Category findByNameAndLevel(String name, Long level);
}
