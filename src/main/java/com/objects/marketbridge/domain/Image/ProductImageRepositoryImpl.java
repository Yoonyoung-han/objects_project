package com.objects.marketbridge.domain.Image;

import com.objects.marketbridge.domain.model.ProductImage;
import com.objects.marketbridge.domain.product.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductImageRepositoryImpl implements ProductImageRepository{

    private final ProductImageJpaRepository productImageJpaRepository;
    private final ProductRepository productRepository;
    private final EntityManager em;

    @Override
    public void save(ProductImage productImage) {
        productImageJpaRepository.save(productImage);
    }

    public List<ProductImage> findAllByProductId(Long productId){
        return em.createQuery("select pi from ProductImage pi where pi.product = :product", ProductImage.class)
                .setParameter("product", productRepository.findById(productId))
                .getResultList();
    }

    public void delete(ProductImage productImage) {
        productImageJpaRepository.delete(productImage);
    }
}
