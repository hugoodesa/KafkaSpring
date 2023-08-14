package br.stapassoli.kafkaConsumer.configuration;

import br.stapassoli.kafkaConsumer.model.Person;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class JsonConsumerConfiguration {

    private final KafkaProperties properties;

    @Bean
    public ConsumerFactory<String, Person> personConsumerFactory() {
        Map<String, Object> configs = new HashMap<>();

        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        configs.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        var jsonDerializer = new JsonDeserializer<>(Person.class)
                .trustedPackages("*")
                .forKeys();

        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer() ,jsonDerializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String , Person> personListnerContainer () {
        var factory = new ConcurrentKafkaListenerContainerFactory<String , Person>();
        factory.setConsumerFactory(personConsumerFactory());
        return factory;
    }



}
