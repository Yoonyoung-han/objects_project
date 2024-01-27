package com.objects.marketbridge.product.Image;

import com.objects.marketbridge.common.infra.entity.ImageEntity;

public interface ImageRepository {

    void save(ImageEntity image);

    ImageEntity findById(Long id);
}
