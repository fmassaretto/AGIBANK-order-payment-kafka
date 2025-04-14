package com.fabiomassaretto.orderservice.repositories;

import com.fabiomassaretto.orderservice.domains.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
