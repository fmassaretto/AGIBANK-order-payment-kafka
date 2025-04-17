package com.fabiomassaretto.paymentservice.services;

import com.fabiomassaretto.paymentservice.exceptions.OrderNotFoundException;
import com.fabiomassaretto.paymentservice.kafka.producers.KafkaProducer;
import com.fabiomassaretto.paymentservice.kafka.queries.KafkaQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {
    private final Logger LOG = LoggerFactory.getLogger(PaymentService.class.getName());

    private final KafkaQuery kafkaQuery;
    private final KafkaProducer kafkaProducer;

    @Value("${kafka.topic.orders.payment-confirmed}")
    private String ORDERS_PAYMENT_CONFIRMED_TOPIC;


    public void processPayment(Long orderId) throws Exception {
        // receive from topic orders and check if an order exists if so send to orderPaid
        if (isOrderIdFound(orderId)) {
            sendOrderPaid(orderId);
            return;
        }

        LOG.warn("Order id = {} not found!", orderId);
        throw new OrderNotFoundException("Order id = {} not found!", orderId);
    }

    private void sendOrderPaid(Long orderId) {
        // send to topic orders.payment-confirmed
        kafkaProducer.simpleSend(ORDERS_PAYMENT_CONFIRMED_TOPIC, "orderId", String.valueOf(orderId));

        LOG.info("Order id = {} was sent to topic {}!", orderId, ORDERS_PAYMENT_CONFIRMED_TOPIC);
    }

    private boolean isOrderIdFound(Long orderId) throws Exception {
        return kafkaQuery.isOrderIdFound(String.valueOf(orderId));
    }
}
