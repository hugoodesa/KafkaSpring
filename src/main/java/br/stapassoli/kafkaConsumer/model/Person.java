package br.stapassoli.kafkaConsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Person implements Serializable {

    private Long id;
    private String name;
    private Integer age;

}
