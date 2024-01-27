package com.objects.marketbridge.common.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RewardType {
    MEMBERSHIP(3L),
    EVENT(1L);

    private final Long rate;
}
