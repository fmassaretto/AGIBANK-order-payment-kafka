package com.fabiomassaretto.payment_service.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
public class PaymentController {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.payment-confirmed}")
    private String TOPIC_PAYMENTS_CONFIRMED;

    /*
    *
    * */
    @PostMapping("/{orderId}")
    public ResponseEntity<String> confirmPayment(@PathVariable Long orderId) {
        kafkaTemplate.send(TOPIC_PAYMENTS_CONFIRMED, "orderId", String.valueOf(orderId));

        LOG.info("Confirm payment for orderId: {}", orderId);

        return ResponseEntity.ok("Payment confirmed for order " + orderId);
    }
}
