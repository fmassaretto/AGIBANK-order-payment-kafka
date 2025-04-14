package com.fabiomassaretto.orderservice.kafka.listener;

import com.fabiomassaretto.orderservice.domains.enums.OrderStatus;
import com.fabiomassaretto.orderservice.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentListener {
    private final OrderService orderService;

    @KafkaListener(topics = "#{'${kafka.topic.payment-confirmed}'}", groupId = "order-service")
    public void listen(String message) {
        Long orderId = Long.valueOf(message);
        orderService.updateStatus(orderId, OrderStatus.PAID);
    }
}
