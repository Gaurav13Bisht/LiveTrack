package com.bisht.customer.service;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @RetryableTopic(attempts = "4",  // Number of retry attempts (default is 3)
            backoff = @Backoff(delay = 2000, multiplier = 2),  // Initial delay and multiplier for exponential backoff
            include = {RuntimeException.class},  // Exceptions to retry
            exclude = {IllegalArgumentException.class}) // Exceptions not to retry
    @KafkaListener(topics = "delivery-agt-topic", groupId = "group-1")
    public void locationUpdate(Integer distance){
        if(distance < 0){
            throw new RuntimeException("Distance is negative !!");
        }
        System.out.println("Delivery agent is only " + distance + "m away from your location !");
    }

    @DltHandler         // All the messages sent to any topic's DLT (in this same class) after retry exhaust
                        // will come here for further processing
                        // BY DEFAULT, the DLT topic is the main topic + "-dlt" so even if you want to handle each topic's
                        // DLT separately, you can create kafka listeners with topic = "mainTopic-dlt".
    public void dltHandler(Integer distance){
        System.out.println("Message received in Dead Letter Topic: " + distance);
    }
}
