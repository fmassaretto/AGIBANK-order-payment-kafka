package com.fabiomassaretto.orderservice.domains;

import com.fabiomassaretto.orderservice.domains.enums.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@Entity
@Table(name = "order_tb")
@NoArgsConstructor
@AllArgsConstructor
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
