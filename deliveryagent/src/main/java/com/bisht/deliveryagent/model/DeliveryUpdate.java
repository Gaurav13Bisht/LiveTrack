package com.bisht.deliveryagent.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeliveryUpdate {
    private String distanceRemaining;
    private String driverName;
    private String estimatedTimeInMins;
}