package com.fabiomassaretto.orderservice.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String description;
    private BigDecimal amount;
    private int quantity;
}
