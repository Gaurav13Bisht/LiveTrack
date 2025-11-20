package com.bisht.customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryUpdate {
    private String distanceRemaining;
    private String driverName;
    private String estimatedTimeInMins;
}