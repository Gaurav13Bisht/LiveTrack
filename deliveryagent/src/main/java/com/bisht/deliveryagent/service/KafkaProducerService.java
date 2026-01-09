package com.bisht.deliveryagent.service;

import com.bisht.deliveryagent.constants.DeliveryConstants;
import com.bisht.deliveryagent.model.DeliveryUpdateAVRO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {

    @Autowired
//    private KafkaTemplate<Integer, DeliveryUpdate> kafkaTemplate; // Data types denotes what type of data is used in its functions like send
    private KafkaTemplate<Integer, DeliveryUpdateAVRO> kafkaTemplate;

    public boolean updateLocation(String remainingDistance){
        try {

            log.info("Gaurav1");
            kafkaTemplate.send(DeliveryConstants.TOPIC_NAME, DeliveryUpdateAVRO.newBuilder()
                            .setDistanceRemaining(remainingDistance)
                            .setDriverName("Not Gaurav")
                            .setStatus("Okay")
                            .setEstimatedTimeInMins("45").build());

            // Can send key as well, all the data with same key gets stored in same partition
            // Useful for scenarios where multiple data needs to be processed in order since each partition maintains order
            // kafkaTemplate.send(DeliveryConstants.TOPIC_NAME, "key", DeliveryUpdateAVRO.newBuilder()
            //                 .setDistanceRemaining(remainingDistance)
            //                 .setDriverName("Not Gaurav")
            //                 .setStatus("Okay")
            //                 .setEstimatedTimeInMins("45").build());

//            kafkaTemplate.send(DeliveryConstants.TOPIC_NAME, DeliveryUpdate.builder().distanceRemaining(remainingDistance)
//                    .driverName("Not Gaurav")
//                    .estimatedTimeInMins("45")
//                    .build());
//            kafkaTemplate.send(DeliveryConstants.TOPIC_NAME, remainingDistance);
            // We can also mention partition number or key if want to send to a particular partition
            // Here partition = 0 and key = 12
//            kafkaTemplate.send(DeliveryConstants.TOPIC_NAME, 0, 12, remainingDistance);
        }
        catch (Exception e){
            log.info("Gaurav");
            log.error(e.toString());
            log.error(String.valueOf(e.getCause()));
            log.error(e.getLocalizedMessage());
            log.error(e.getMessage());
            return false;
        }
        return true;
    }
}
