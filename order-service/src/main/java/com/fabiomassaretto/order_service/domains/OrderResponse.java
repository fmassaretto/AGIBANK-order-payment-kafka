package com.fabiomassaretto.order_service.domains;

import com.fabimassaretto.commons.domains.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private long id;
    private String description;
    private BigDecimal amount;
    private int quantity;
    private OrderStatus status;
}
