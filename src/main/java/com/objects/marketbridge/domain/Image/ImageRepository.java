package com.objects.marketbridge.domain.Image;

import com.objects.marketbridge.domain.model.Image;

import java.util.List;

public interface ImageRepository {

    void save(Image image);

    Image findById(Long id);

    void delete(Image image);

    void deleteById(Long id);
}
