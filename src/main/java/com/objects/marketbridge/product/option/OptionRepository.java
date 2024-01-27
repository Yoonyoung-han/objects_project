package com.objects.marketbridge.product.option;

import com.objects.marketbridge.common.infra.entity.Option;

public interface OptionRepository {

    void save(Option option);

    Option findById(Long id);

    Option findByName(String name);
}
