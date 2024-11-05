package com.tracking.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TrackingNumberResponse {

    private String trackingNumber;
    private String createdAt;

    public TrackingNumberResponse(String trackingNumber, String createdAt) {
        this.trackingNumber = trackingNumber;
        this.createdAt = createdAt;
    }

    // Getters and Setters
}
