package com.fabiomassaretto.order_service.controllers;

import com.fabiomassaretto.order_service.domains.OrderRequest;
import com.fabiomassaretto.order_service.domains.OrderResponse;
import com.fabiomassaretto.order_service.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody OrderRequest orderRequest) {
        OrderResponse response = orderService.create(orderRequest);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getAll() {
        List<OrderResponse> response = orderService.getAll();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
