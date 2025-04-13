package com.fabiomassaretto.order_service.domains;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String description;
    private BigDecimal amount;
    private int quantity;
}
