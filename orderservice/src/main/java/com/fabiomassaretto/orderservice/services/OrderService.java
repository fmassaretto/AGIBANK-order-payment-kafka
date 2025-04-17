package com.fabiomassaretto.orderservice.services;

import com.fabiomassaretto.orderservice.domains.OrderEntity;
import com.fabiomassaretto.orderservice.domains.OrderRequest;
import com.fabiomassaretto.orderservice.domains.OrderResponse;
import com.fabiomassaretto.orderservice.domains.enums.OrderStatus;
import com.fabiomassaretto.orderservice.domains.mappers.OrderMapper;
import com.fabiomassaretto.orderservice.exceptions.OrderNotFoundException;
import com.fabiomassaretto.orderservice.kafka.producers.KafkaProducer;
import com.fabiomassaretto.orderservice.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    private final KafkaProducer kafkaProducer;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Value("${kafka.topic.orders}")
    private String TOPIC_ORDERS;

    public OrderResponse create(OrderRequest orderRequest) {
        OrderEntity entity = orderMapper.mapOrderRequestToEntity(orderRequest);

        entity.setStatus(OrderStatus.PENDING_PAYMENT);

        OrderEntity orderEntity = orderRepository.save(entity);

        LOG.info("Order created: {}", orderEntity);

        // produce to topic orders
        String orderId = String.valueOf(orderEntity.getId());

        kafkaProducer.simpleSend(TOPIC_ORDERS, "orderId", orderId);

        LOG.info("OrderId sent to topic: {} with message: {}", TOPIC_ORDERS, orderId);

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
