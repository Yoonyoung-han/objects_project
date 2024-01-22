package com.objects.marketbridge.domain.Image;

import com.objects.marketbridge.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageJpaRepository extends JpaRepository<Image, Long> {
}
