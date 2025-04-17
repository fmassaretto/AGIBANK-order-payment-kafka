package com.fabiomassaretto.paymentservice.kafka.queries;

import com.fabiomassaretto.paymentservice.configs.KafkaConsumerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaQuery {
    private static final Logger LOG = LoggerFactory.getLogger(KafkaQuery.class);

    private final KafkaConsumerConfig kafkaConsumerConfig;

    @Value("${kafka.bootstrapServer}")
    private String BOOTSTRAP_SERVERS;

    @Value("${kafka.topic.orders}")
    private String ORDERS_TOPIC;

    public boolean isOrderIdFound(String orderId) throws Exception {
        Properties props = kafkaConsumerConfig.getConsumerProperties();

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props)) {
            TopicPartition partition = new TopicPartition(ORDERS_TOPIC, 0);
            consumer.assign(Collections.singletonList(partition));
            consumer.seekToBeginning(Collections.singletonList(partition));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                if (records.isEmpty()) break;

                for (ConsumerRecord<String, String> record : records) {
                    String consumerOrderId = record.value();
                    if (orderId.equals(consumerOrderId)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            String message = "Error while checking order id " + orderId;
            LOG.error(message, e);
            throw new Exception(message);
        }

        LOG.warn("Order id {} was not found", orderId);

        return false; // not found
    }
}
