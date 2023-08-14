package br.stapassoli.kafkaProduce.kafkaProducer.configuration;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ProducerFactoryConfiguration {

    private final KafkaProperties properties;

    @Bean
    public ProducerFactory<String, String> factory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public ProducerFactory<String, Object> jsonProducerFactory() {
        Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config, new StringSerializer(), new JsonSerializer<>());
    }

    @Bean KafkaTemplate<String, Object> jsonKafkaTemplate () {
        return new KafkaTemplate<>(jsonProducerFactory());
    }

    @Bean
    public KafkaTemplate<String, String> template() {
        return new KafkaTemplate<>(factory());
    }

    @Bean
    public KafkaAdmin admin() {
        Map<String, Object> config = new HashMap<>();
        config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
        return new KafkaAdmin(config);
    }

    @Bean
    public KafkaAdmin.NewTopics createFirstTopic() {

        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("topic-001").partitions(2).replicas(1).build(),
                TopicBuilder.name("person-topic").partitions(2).replicas(1).build(),
                TopicBuilder.name("city-topic").partitions(2).replicas(1).build()
        );

    }


}
