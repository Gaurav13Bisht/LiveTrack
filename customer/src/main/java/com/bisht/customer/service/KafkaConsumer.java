package com.bisht.customer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "delivery-agt-topic", groupId = "group-1")
    public void locationUpdate(Integer distance){
        System.out.println("Delivery agent is only " + distance + "m away from your location !");
    }
}
