package com.fabiomassaretto.paymentservice.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PaymentControllerTest {

    private final KafkaTemplate<String, String> kafkaTemplate = mock(KafkaTemplate.class);

    MockMvc mockWvc;

    @BeforeEach
    void setUp() {
        this.mockWvc = MockMvcBuilders.standaloneSetup(new PaymentController(kafkaTemplate)).build();
    }

    @Test
    @DisplayName("When confirmPayment is called then should return message successfully")
    void whenConfirmPaymentIsCalledThenShouldReturnMessageSuccessfully() throws Exception {
        MvcResult result = mockWvc.perform(post("/payments/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        assertEquals( "Payment confirmed for order 1", result.getResponse().getContentAsString());
    }
}