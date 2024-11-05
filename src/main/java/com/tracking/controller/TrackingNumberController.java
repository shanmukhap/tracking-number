package com.tracking.controller;

import com.tracking.model.TrackingNumberResponse;
import com.tracking.service.TrackingNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class TrackingNumberController {

    private final TrackingNumberService trackingNumberService;

    @Autowired
    public TrackingNumberController(TrackingNumberService trackingNumberService) {
        this.trackingNumberService = trackingNumberService;
    }

    @GetMapping("/next-tracking-number")
    public TrackingNumberResponse getNextTrackingNumber(
            @RequestParam("origin_country_id") String originCountryId,
            @RequestParam("destination_country_id") String destinationCountryId,
            @RequestParam("weight") double weight,
            @RequestParam("created_at") String createdAt,
            @RequestParam("customer_id") UUID customerId,
            @RequestParam("customer_name") String customerName,
            @RequestParam("customer_slug") String customerSlug) {

        String trackingNumber = trackingNumberService.generateTrackingNumber(
                originCountryId, destinationCountryId, weight, createdAt, customerId, customerName, customerSlug);

        return new TrackingNumberResponse(trackingNumber, Instant.now().toString());
    }
}
