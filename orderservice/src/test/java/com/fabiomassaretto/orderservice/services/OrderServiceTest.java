package com.fabiomassaretto.orderservice.services;

import com.fabiomassaretto.orderservice.domains.OrderEntity;
import com.fabiomassaretto.orderservice.domains.OrderRequest;
import com.fabiomassaretto.orderservice.domains.OrderResponse;
import com.fabiomassaretto.orderservice.domains.enums.OrderStatus;
import com.fabiomassaretto.orderservice.domains.mappers.OrderMapper;
import com.fabiomassaretto.orderservice.exceptions.OrderNotFoundException;
import com.fabiomassaretto.orderservice.repositories.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

    private OrderEntity orderEntity1;
    private OrderEntity orderEntity2;
    private OrderRequest orderRequest;
    private List<OrderEntity> orderEntityList;
    private List<OrderResponse> orderResponseList;

    @BeforeEach
    public void setUp() {
        setupModels();
    }

    @Test
    @DisplayName("When create method is called then should save it and return order response")
    void whenPassingValidOrderRequestShouldSaveAndReturnOrderResponse() {
        given(orderMapper.mapOrderRequestToEntity(any())).willReturn(orderEntity1);
        given(orderRepository.save(any())).willReturn(orderEntity2);
        given(orderMapper.mapOrderEntityToResponse(orderEntity2)).willReturn(orderResponseList.getFirst());

        OrderResponse result = orderService.create(orderRequest);

        assertEquals(orderResponseList.getFirst(), result);
        assertEquals(orderResponseList.getFirst().getDescription(), result.getDescription());
        assertEquals(orderResponseList.getFirst().getAmount(), result.getAmount());
        assertEquals(orderResponseList.getFirst().getQuantity(), result.getQuantity());
        assertEquals(orderResponseList.getFirst().getStatus(), result.getStatus());
    }

    @Test
    @DisplayName("When updateStatus method is called then should be called once")
    void whenUpdateStatusMethodIsCalledThenShouldBeCalledOnce() {
        given(orderRepository.findById(any())).willReturn(Optional.ofNullable(orderEntity1));
        given(orderRepository.save(any())).willReturn(orderEntity1);

        orderService.updateStatus(1L, OrderStatus.PAID);

        verify(orderRepository, times(1)).save(orderEntity1);
    }

    @Test
    @DisplayName("When updateStatus method is called and finById return empty should throw OrderNotFoundException")
    void whenUpdateStatusMethodIsCalledAndFinByIdReturnEmptyShouldThrowOrderNotFoundException() {
        given(orderRepository.findById(any())).willReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.updateStatus(3L, OrderStatus.PAID));
    }

    @Test
    @DisplayName("When getAll method is called then should return list of orders")
    void whenGetAllMethodIsCalledThenShouldReturnListOfOrders() {
        given(orderMapper.mapOrderEntityListToResponse(any())).willReturn(orderResponseList);

        given(orderRepository.findAll()).willReturn(orderEntityList);

        List<OrderResponse> result = orderService.getAll();

        assertEquals(orderResponseList, result);
        assertEquals(orderResponseList.getFirst().getId(), result.getFirst().getId());
        assertEquals(orderResponseList.get(1).getId(), result.get(1).getId());
    }


    private void setupModels() {
        orderEntity1 = OrderEntity.builder()
                .id(1L)
                .description("Order 1")
                .amount(BigDecimal.valueOf(100.00))
                .quantity(1)
                .status(OrderStatus.PENDING_PAYMENT)
                .build();

        orderEntity2 = OrderEntity.builder()
                .id(2L)
                .description("Order 2")
                .amount(BigDecimal.valueOf(80.00))
                .quantity(2)
                .status(OrderStatus.PAID)
                .build();

        orderRequest = OrderRequest.builder()
                .description("Order Request")
                .amount(BigDecimal.valueOf(10.00))
                .quantity(10)
                .build();

        orderEntityList = List.of(orderEntity1, orderEntity2);

        orderResponseList = orderEntityList.stream().map(item -> OrderResponse.builder()
                .id(item.getId())
                .description(item.getDescription())
                .amount(item.getAmount())
                .quantity(item.getQuantity())
                .status(item.getStatus())
                .build()).toList();
    }
}