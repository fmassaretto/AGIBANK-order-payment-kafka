package com.fabiomassaretto.orderservice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderNotFoundException extends RuntimeException {
    private static final Logger LOG = LoggerFactory.getLogger(OrderNotFoundException.class);

    public OrderNotFoundException(String message) {
        super(message);
        LOG.error(message);
    }
}
