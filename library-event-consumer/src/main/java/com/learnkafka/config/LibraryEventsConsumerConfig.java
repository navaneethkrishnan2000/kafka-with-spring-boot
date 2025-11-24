package com.learnkafka.config;

import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

@EnableKafka
@Configuration
public class LibraryEventsConsumerConfig {

    @Bean
    ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            ConsumerFactory<Object, Object> kafkaConsumerFactory
    ) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory
            = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, kafkaConsumerFactory);

//        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL); // This configuration explicitly overrides the default AckMode.BATCH (or AckMode.RECORD in some defaults) and instructs the Spring Kafka container not to commit offsets automatically. Instead, it delegates the responsibility to the application code via the Acknowledgment object.

        factory.setConcurrency(3);
        /**
            It enables multithreaded, parallel message processing within a single application instance.

         Multiple Consumer Instances: - Instead of running just one consumer that handles all assigned KafkaMessageListenerContainer instances. Each of these runs in its own dedicated thread.
         Partition Distribution: -  These consumer instances all belong to the same consumer group (defined by the group.is property).
                                    Kafka's group management automatically distributes the topic's partitioning among these consumers.
         Parallel Processing: - Since each consumer instance operates on its assigned partitions in parallel using separate threads, the application's overall message throughput can increase significantly.
         */

        return factory;
    }
}
