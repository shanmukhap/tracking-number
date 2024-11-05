package com.tracking.service;

import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

@Service
public class TrackingNumberService {

    private static final String CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int TRACKING_NUMBER_LENGTH = 16;

    public String generateTrackingNumber(String originCountryId, String destinationCountryId, double weight,
                                         String createdAt, UUID customerId, String customerName, String customerSlug) {

        String input = originCountryId + destinationCountryId + weight + createdAt + customerId.toString() + customerName + customerSlug;
        return generateHash(input);
    }

    private String generateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            String hash = Base64.getUrlEncoder().withoutPadding().encodeToString(hashBytes);
            return hash.substring(0, TRACKING_NUMBER_LENGTH); // Take first 16 characters
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}
