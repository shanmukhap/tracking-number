package com.tracking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisTrackingService {

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public RedisTrackingService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean isTrackingNumberUnique(String trackingNumber) {
        return redisTemplate.opsForValue().setIfAbsent(trackingNumber, "1");
    }

    public void storeTrackingNumber(String trackingNumber) {
        redisTemplate.opsForValue().set(trackingNumber, "1");
    }
}
