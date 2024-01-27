package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.OptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionJpaRepository extends JpaRepository<OptionEntity, Long> {

    OptionEntity findByName(String name);
}
