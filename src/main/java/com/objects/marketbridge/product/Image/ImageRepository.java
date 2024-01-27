package com.objects.marketbridge.product.Image;

import com.objects.marketbridge.common.infra.entity.Image;

public interface ImageRepository {

    void save(Image image);

    Image findById(Long id);
}
