package com.fabiomassaretto.paymentservice.exceptions;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message, Long orderId) {
        super(String.format(message, orderId));
    }
}
