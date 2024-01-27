package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageJpaRepository extends JpaRepository<Image, Long> {
}
