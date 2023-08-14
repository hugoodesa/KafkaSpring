package br.stapassoli.kafkaProduce.kafkaProducer.service;

import br.stapassoli.kafkaProduce.kafkaProducer.model.City;
import br.stapassoli.kafkaProduce.kafkaProducer.model.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerFactoryService {

    private final KafkaTemplate template;

    private final KafkaTemplate jsonKafkaTemplate;

    public void sendMessage(String message) {
        template.send("topic-001", message);
    }

    public void sendPerson(Person person) {
        this.jsonKafkaTemplate.send("person-topic", person);
    }

    public void sendCity(City city) {
        this.jsonKafkaTemplate.send("city-topic",city);
    }
}
