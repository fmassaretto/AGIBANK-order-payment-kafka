package com.fabiomassaretto.orderservice.controllers;

import com.fabiomassaretto.orderservice.domains.OrderEntity;
import com.fabiomassaretto.orderservice.domains.OrderRequest;
import com.fabiomassaretto.orderservice.domains.OrderResponse;
import com.fabiomassaretto.orderservice.domains.enums.OrderStatus;
import com.fabiomassaretto.orderservice.services.OrderService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class OrderControllerTest {

    MockMvc mockWvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    private OrderRequest orderRequest;
    private List<OrderResponse> orderResponseList;

    @BeforeEach
    void setUp() {
        setupModels();
        this.mockWvc = MockMvcBuilders.standaloneSetup(new OrderController(orderService)).build();
    }

    @Test
    @DisplayName("When create endpoint is called then should return created order")
    void whenCreateEndpointIsCalledThenShouldReturnCreatedOrder() throws Exception {
        when(orderService.create(any())).thenReturn(orderResponseList.getFirst());

        MvcResult result = mockWvc.perform(post("/orders")
                        .content(new Gson().toJson(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn();

        ResponseEntity<OrderResponse> actual = orderController.create(orderRequest);

        assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        assertEquals(actual.getBody(), orderResponseList.getFirst());
        assertEquals(new Gson().toJson(actual.getBody()), result.getResponse().getContentAsString());
    }

    @Test
    @DisplayName("When getAll endpoint is called then should return list of order")
    void whenGetAllEndpointIsCalledThenShouldReturnListOfOrder() throws Exception {
        when(orderService.getAll()).thenReturn(orderResponseList);

        MvcResult result = mockWvc.perform(get("/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        ResponseEntity<List<OrderResponse>> actual = orderController.getAll();

        assertEquals(actual.getStatusCode().value(), result.getResponse().getStatus());
        assertEquals(actual.getBody(), orderResponseList);
        assertEquals(Objects.requireNonNull(actual.getBody()).size(), orderResponseList.size());
        assertEquals(new Gson().toJson(actual.getBody()), result.getResponse().getContentAsString());
    }


    private void setupModels() {
        OrderEntity orderEntity1 = OrderEntity.builder()
                .id(1L)
                .description("Order 1")
                .amount(BigDecimal.valueOf(100.00))
                .quantity(1)
                .status(OrderStatus.PENDING_PAYMENT)
                .build();

        OrderEntity orderEntity2 = OrderEntity.builder()
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

        List<OrderEntity> orderEntityList = List.of(orderEntity1, orderEntity2);

        orderResponseList = orderEntityList.stream().map(item -> OrderResponse.builder()
                .id(item.getId())
                .description(item.getDescription())
                .amount(item.getAmount())
                .quantity(item.getQuantity())
                .status(item.getStatus())
                .build()).toList();
    }
}