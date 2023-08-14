package br.stapassoli.kafkaProduce.kafkaProducer.controller;

import br.stapassoli.kafkaProduce.kafkaProducer.model.Person;
import br.stapassoli.kafkaProduce.kafkaProducer.service.ProducerFactoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/v1/factory")
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerFactoryService producerFactoryService;

    @PostMapping
    public void message(@RequestBody String message) {
        producerFactoryService.sendMessage(message);
    }

    @PostMapping("/many")
    public void many() {

        IntStream.range(1, 3).forEach(numero -> {
            producerFactoryService.sendMessage(numero+"");
        });

    }

    @PostMapping("/person")
    public void many(@RequestBody Person person) {

        producerFactoryService.sendPerson(person);

    }


}
