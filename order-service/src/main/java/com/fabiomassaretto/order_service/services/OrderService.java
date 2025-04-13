package com.fabiomassaretto.order_service.services;

import com.fabimassaretto.commons.domains.enums.OrderStatus;
import com.fabiomassaretto.order_service.domains.OrderEntity;
import com.fabiomassaretto.order_service.domains.OrderRequest;
import com.fabiomassaretto.order_service.domains.OrderResponse;
import com.fabiomassaretto.order_service.domains.mappers.OrderMapper;
import com.fabiomassaretto.order_service.exceptions.OrderNotFoundException;
import com.fabiomassaretto.order_service.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderResponse create(OrderRequest orderRequest) {
        OrderEntity entity = orderMapper.mapOrderRequestToEntity(orderRequest);

        entity.setStatus(OrderStatus.PENDING_PAYMENT);

        OrderEntity orderEntity = orderRepository.save(entity);

        LOG.info("Order created: {}", orderEntity);

        return orderMapper.mapOrderEntityToResponse(orderEntity);
    }

    public void updateStatus(Long id, OrderStatus status) {
        OrderEntity entity = orderRepository
                .findById(id)
                .orElseThrow(() -> new OrderNotFoundException("An order with id = " + id + " does not exist"));

        entity.setStatus(status);

        LOG.info("Updating status of an order with id = {} to {}", id, status);

        orderRepository.save(entity);
    }

    public List<OrderResponse> getAll() {
        List<OrderEntity> orderEntity = orderRepository.findAll();

        return orderMapper.mapOrderEntityListToResponse(orderEntity);
    }
}
