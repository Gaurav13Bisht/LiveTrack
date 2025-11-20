package com.bisht.customer.service;

import com.bisht.customer.model.DeliveryUpdate;
import com.bisht.deliveryagent.model.DeliveryUpdateAVRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    @RetryableTopic(attempts = "5",  // Number of retry attempts (default is 3)
            backoff = @Backoff(delay = 2000, multiplier = 2),      // Initial delay and multiplier for exponential backoff
//            include = {RuntimeException.class},  // Exceptions to retry
            exclude = {IllegalArgumentException.class}) // Exceptions not to retry

    // 1. If we dont use @RetryableTopic, retries still happens by with default attempt (9 in this kafka dependency version)
    // 2. We use @RetryableTopic to customise the retrying
    @KafkaListener(topics = "delivery-agt-topic", groupId = "group-1")
    // Here groupId is consumer group name.
    // Can mention partition id as well but not recommended since if this app gets replicated, mutliple
    // consumer will read from same partition and result in duplicate processing
    // @KafkaListener(
    //    groupId = "group-1",
    //    topicPartitions = {
    //        @TopicPartition(topic = "delivery-agt-topic", partitions = {"0", "1"})
    //    }
    //)
//    public void locationUpdate(DeliveryUpdate deliveryUpdate){
    public void locationUpdate(DeliveryUpdateAVRO deliveryUpdate){
//        if(Integer.parseInt(distance) < 0){
//            throw new IllegalArgumentException("Distance is negative !!");
//        }
//        System.out.println("Delivery agent is only " + distance + "m away from your location !");
        log.info(deliveryUpdate.toString());
    }


    @DltHandler
    // 1. Whenever there is a exception thrown by method annotated with @KafkaListener, the message will go to
    // DLT topic (either directly if retry is excluded or after retries)
    // 2. All the messages sent to any topic's DLT (in this same class) after retry exhaust
    // will come here for further processing
    // 3. BY DEFAULT, the DLT topic is the main topic + "-dlt" so even if you want to handle each topic's
    // DLT separately, you can create kafka listeners with topic = "mainTopic-dlt".
    public void dltHandler(String distance){
        System.out.println("Message received in Dead Letter Topic! Distance was: " + distance);
    }
}
