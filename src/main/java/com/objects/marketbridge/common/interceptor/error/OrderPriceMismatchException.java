package com.objects.marketbridge.common.interceptor.error;

public class OrderPriceMismatchException extends RuntimeException {
    public OrderPriceMismatchException(String message) {
        super(message);
    }
}
