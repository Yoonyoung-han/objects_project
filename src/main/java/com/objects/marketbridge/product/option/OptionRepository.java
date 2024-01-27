package com.objects.marketbridge.product.option;

import com.objects.marketbridge.common.infra.entity.OptionEntity;

public interface OptionRepository {

    void save(OptionEntity option);

    OptionEntity findById(Long id);

    OptionEntity findByName(String name);
}
