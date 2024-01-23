package com.objects.marketbridge.domain.category.repository;

import com.objects.marketbridge.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, Long> {

    Boolean existsByName(String name);

    Category findByName(String name);

    Boolean existsByNameAndLevel(String name, Long level);

    Category findByNameAndLevel(String name, Long level);
}
