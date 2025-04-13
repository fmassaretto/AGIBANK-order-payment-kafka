package com.fabimassaretto.commons.domains;

import com.fabimassaretto.commons.domains.enums.OrderStatus;

import java.math.BigDecimal;

public record Order(long id, BigDecimal amount, int quantity, String description, OrderStatus orderStatus) {
}
