package com.learnkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

//@Component
@Slf4j
public class LibraryEventsConsumerManualOffset
        implements AcknowledgingMessageListener<Integer, String> {

    @Override
    @KafkaListener(
            topics = {"library-events"}
    )
    public void onMessage(ConsumerRecord<Integer, String> consumerRecord, Acknowledgment acknowledgment) {
        log.info("ConsumerRecord: {}", consumerRecord);
        acknowledgment.acknowledge(); // This line is where the manual commit happens. Calling this method informs Spring Kafka that the message has been successfully processed and its offset can be committed to the __consumer_offsets topic on the broker.
    }
}

/**
 *  implements AcknowledgingMessageListener<Integer, String> :- By implementing this interface, you are telling Spring Kafka that your listener method requires an additional parameter (Acknowledgment) to manually handle the commit process.

 Summary of the Flow
    1. A message is received from the library-events topic.
    2. The onMessage method processes the message (logging it in your current code).
    3. Once the processing is complete (the log.info line finishes), the code calls acknowledgment.acknowledge().
    4. Spring Kafka internally sends a commit request to the Kafka broker for that specific message's offset.
    5. The consumer proceeds to process the next message.
 This pattern is highly recommended for production applications where reliable processing and data integrity are critical, as it ensures that no message is marked as consumed until the application confirms it is done with it.

 */
