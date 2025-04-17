package com.fabiomassaretto.paymentservice.controllers;

import com.fabiomassaretto.paymentservice.exceptions.OrderNotFoundException;
import com.fabiomassaretto.paymentservice.services.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/payments")
@Slf4j
public class PaymentController {
    private static final Logger LOG = LoggerFactory.getLogger(PaymentController.class);

    private final PaymentService paymentService;

    @PostMapping("/{orderId}")
    public ResponseEntity<String> confirmPayment(@PathVariable Long orderId) {
        String message = "";
        try {
            paymentService.processPayment(orderId);
        } catch (OrderNotFoundException e) {
            message = "Order id = " + orderId + " not found!";

            LOG.warn(message);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
        } catch (Exception e) {
            message = "Error while processing payment! Error message: " + e.getMessage();

            LOG.warn(message);

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
        }

        message = "Payment confirmed for order " + orderId;

        LOG.info(message);

        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}
