package com.fabiomassaretto.order_service.kafka.listener;

import com.fabiomassaretto.order_service.domains.enums.OrderStatus;
import com.fabiomassaretto.order_service.services.OrderService;
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
