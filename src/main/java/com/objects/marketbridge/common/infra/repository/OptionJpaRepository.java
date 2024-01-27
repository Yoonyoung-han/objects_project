package com.objects.marketbridge.common.infra.repository;

import com.objects.marketbridge.common.infra.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionJpaRepository extends JpaRepository<Option, Long> {

    Option findByName(String name);
}
