package br.stapassoli.kafkaConsumer.listner;

import br.stapassoli.kafkaConsumer.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.adapter.ConsumerRecordMetadata;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaListner {

    @KafkaListener(topics = "topic-001", groupId = "group-1")
    public void listen(String message, ConsumerRecordMetadata metadata) {
        log.info("Topic , {}", metadata.topic());
        log.info("Partition , {}", metadata.partition());
        log.info("offset , {}", metadata.offset());
        log.info("Thread , {} ", Thread.currentThread().getName());
        log.info("recieved message , {} ", message);
    }


    @KafkaListener(topics = "person-topic", groupId = "group-1" , containerFactory = "personListnerContainer")
    public void listenPerson(Person person) {
        log.info("recieved person , {} ", person);
    }

}
