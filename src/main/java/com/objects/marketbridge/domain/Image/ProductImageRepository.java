package com.objects.marketbridge.domain.Image;

import com.objects.marketbridge.domain.model.Image;
import com.objects.marketbridge.domain.model.ProductImage;

import java.util.List;

public interface ProductImageRepository {

    void save(ProductImage productImage);

    public List<ProductImage> findAllByProductId(Long productId);

    void delete(ProductImage productImage);
}
