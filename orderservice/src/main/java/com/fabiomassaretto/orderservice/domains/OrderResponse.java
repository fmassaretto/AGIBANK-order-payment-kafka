package com.fabiomassaretto.orderservice.domains;

import com.fabiomassaretto.orderservice.domains.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private long id;
    private String description;
    private BigDecimal amount;
    private int quantity;
    private OrderStatus status;
}
