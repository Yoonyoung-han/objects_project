package com.objects.marketbridge.common.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Membership {
    BASIC("일반"),
    WOW("와우");

    private final String text;
}
