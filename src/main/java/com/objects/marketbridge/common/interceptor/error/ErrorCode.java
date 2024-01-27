package com.objects.marketbridge.common.interceptor.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    INVALID_PAYMENT_AMOUNT("유효하지 않은 액수."),
    MISMATCHED_PAYMENT_DATA("결제정보 검증에 실패하였습니다."),
    SHIPPING_ADDRESS_NOT_REGISTERED("등록된 배송지 정보가 없습니다."),
    PAYMENT_NOT_FOUND("결제 정보를 찾을 수 없습니다."),
    OUT_OF_STOCK("재고가 없습니다."),
    SESSION_EXPIRED("세션값이 만료되었습니다");

    private final String message;
}
