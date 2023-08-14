package br.stapassoli.kafkaConsumer.listner;

import br.stapassoli.kafkaConsumer.annotations.PersonListnerAnnotation;
import br.stapassoli.kafkaConsumer.model.City;
import br.stapassoli.kafkaConsumer.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListnerConsumer {

    /*@KafkaListener(topics = "topic-001", groupId = "group-1")
    public void listen(String message, ConsumerRecordMetadata metadata) {
        log.info("Topic , {}", metadata.topic());
        log.info("Partition , {}", metadata.partition());
        log.info("offset , {}", metadata.offset());
        log.info("Thread , {} ", Thread.currentThread().getName());
        log.info("recieved message , {} ", message);
    }*/


    @PersonListnerAnnotation(groupId = "group-1")
    public void listenPerson(Person person) {
        log.info("Thread , {} ", Thread.currentThread().getName());
        log.info("recieved person , {} ", person);
    }

    @KafkaListener(
            topicPartitions = {
                    @TopicPartition(topic = "topic-001", partitions = "1")
            },
            topics = "topic-001",
            groupId = "group-2"
    )
    public void listenToSpecificPartitionOne(String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int idPartition) {
        log.info("Partition id , {} ", idPartition);
        log.info("recieved message , {} ", message);
    }

    @KafkaListener(
            topicPartitions = {
                    @TopicPartition(topic = "topic-001", partitions = "0")
            },
            topics = "topic-001",
            groupId = "group-2"
    )
    public void listenToSpecificPartitionZero(String message, @Header(KafkaHeaders.RECEIVED_PARTITION) int idPartition) {
        log.info("Partition id , {} ", idPartition);
        log.info("recieved message , {} ", message);
    }

    @KafkaListener(topics = "city-topic", groupId = "group-01")
    public void listenCity(City city) {
        log.info("City message , {} ", city);
    }


}
