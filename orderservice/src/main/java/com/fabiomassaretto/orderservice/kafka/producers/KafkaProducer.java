package com.fabiomassaretto.orderservice.kafka.producers;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void simpleSend(String topic, String key, String message) {
        kafkaTemplate.send(topic, key, message);
    }
}
