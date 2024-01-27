package com.objects.marketbridge.product.Image;

import com.objects.marketbridge.common.infra.repository.ImageJpaRepository;
import com.objects.marketbridge.common.infra.entity.ImageEntity;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {

    private final ImageJpaRepository imageJpaRepository;

    @Override
    public void save(ImageEntity image) {
        imageJpaRepository.save(image);
    }

    @Override
    public ImageEntity findById(Long id) {
        return imageJpaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
