package com.bisht.deliveryagent.config;

import com.bisht.deliveryagent.constants.DeliveryConstants;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name(DeliveryConstants.TOPIC_NAME).build();
    }

}
