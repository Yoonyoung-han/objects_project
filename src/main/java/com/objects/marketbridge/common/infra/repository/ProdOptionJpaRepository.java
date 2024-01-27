package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.ProdOptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdOptionJpaRepository extends JpaRepository<ProdOptionEntity, Long> {
}
