package br.stapassoli.kafkaProduce.kafkaProducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person implements Serializable {

    private Long id;
    private String name;
    private Integer age;

}
