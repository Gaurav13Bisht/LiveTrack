package com.bisht.deliveryagent.controller;

import com.bisht.deliveryagent.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery-agent")
public class DeliveryAgtController {

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @PostMapping("/location")
    public ResponseEntity<?> updateLocation(@RequestParam String remainingDistance){
        return kafkaProducerService.updateLocation(remainingDistance) ?
                new ResponseEntity<>("Updated", HttpStatus.ACCEPTED) :
                new ResponseEntity<>("Not Updated", HttpStatus.BAD_REQUEST);
    }
}