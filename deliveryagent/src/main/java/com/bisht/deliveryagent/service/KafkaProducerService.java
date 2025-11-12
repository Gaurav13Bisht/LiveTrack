package com.bisht.deliveryagent.service;

import com.bisht.deliveryagent.constants.DeliveryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate; // Data types denotes what type of data is used in its functions like send

    public boolean updateLocation(String remainingDistance){
        try {
            kafkaTemplate.send(DeliveryConstants.TOPIC_NAME, remainingDistance);
        }
        catch (Exception e){
            return false;
        }
        return true;
    }
}