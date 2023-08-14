package br.stapassoli.kafkaConsumer.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.kafka.annotation.KafkaListener;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@KafkaListener
public @interface PersonListnerAnnotation {

    @AliasFor(annotation = KafkaListener.class, value = "groupId")
    String groupId() default "";

    @AliasFor(annotation = KafkaListener.class, value = "topics")
    String[] topics() default {"person-topic"};

    @AliasFor(annotation = KafkaListener.class, value = "containerFactory")
    String containerFactory() default "personListnerContainer";

}
