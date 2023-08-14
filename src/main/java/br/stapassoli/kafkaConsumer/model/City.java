package br.stapassoli.kafkaConsumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class City implements Serializable {

    private Long id;
    private String nome;
    private String sigla;

}
