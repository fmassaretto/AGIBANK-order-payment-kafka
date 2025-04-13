package com.fabiomassaretto.order_service.repositories;

import com.fabiomassaretto.order_service.domains.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
