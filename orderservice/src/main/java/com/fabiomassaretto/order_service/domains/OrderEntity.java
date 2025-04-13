package com.fabiomassaretto.order_service.domains;

import com.fabiomassaretto.order_service.domains.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "order_tb")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 5, max = 200)
    private String description;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private Integer quantity;
    @NotNull
    private OrderStatus status;
}
