package com.objects.marketbridge.domain.category.repository;

import com.objects.marketbridge.domain.model.Category;
import com.objects.marketbridge.domain.model.ProductImage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository{

    private final CategoryJpaRepository categoryJpaRepository;
    private final EntityManager em;

    @Override
    public Category findById(Long id) {
        return categoryJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Boolean existsByName(String name) {
        return categoryJpaRepository.existsByName(name);
    }

    @Override
    public void save(Category category) {
        categoryJpaRepository.save(category);
    }

    @Override
    public Category findByName(String name) {
        return categoryJpaRepository.findByName(name);
    }

//    public List<ProductImage> findAllByProductId(Long productId){
//        return em.createQuery("select pi from ProductImage pi where pi.product = :product", ProductImage.class)
//                .setParameter("product", productRepository.findById(productId))
//                .getResultList();
//    }

    @Override
    public Category findByNameAndLevel(String name, Long level) {
//        Category category = em.createQuery
//                        ("select c from Category c where c.name = :name and c.level = :level", Category.class)
//                .setParameter("name", name)
//                .setParameter("level", level)
//                .getSingleResult();
        Category category = categoryJpaRepository.findByNameAndLevel(name, level);
        return category;
    }

    @Override
    public Boolean existsByNameAndLevel(String name, Long level) {
        return categoryJpaRepository.existsByNameAndLevel(name, level);
    }


}
