package org.example.domain;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final RedissonClient redissonClient;

    private static final long COUPON_LIMIT = 30;
    private static final String LOCK_KEY = "couponLock"; // Lock key for Redisson


    public RedisService(RedisTemplate<String, Object> redisTemplate, RedissonClient redissonClient) {
        this.redisTemplate = redisTemplate;
        this.redissonClient = redissonClient;
    }

    public void saveValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    // Method to increment the value of "coupon" by 1
    public void incrementCoupon() {
        Long currentValue = getCouponValue();

        if (currentValue >= COUPON_LIMIT) {
            throw new RuntimeException("Coupon limit reached. Cannot increment further.");
        }

        redisTemplate.opsForValue().increment("coupon");
    }

    public String incrementCouponWithLock() {
        RLock lock = redissonClient.getLock(LOCK_KEY);
        try {
            // Try to acquire the lock
            if (lock.tryLock(10, 10, TimeUnit.SECONDS)) {
                Long currentValue = getCouponValue();

                // Double-check the current value after acquiring lock
                if (currentValue >= COUPON_LIMIT) {
                    return "Coupon limit reached. No more increments allowed.";
                }

                // Increment the coupon value
                Long newValue = redisTemplate.opsForValue().increment("coupon");

                return "Coupon incremented successfully. Current value: " + newValue;
            } else {
                return "Could not acquire lock, please try again.";
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "An error occurred while attempting to acquire lock.";
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }

    }

    // Method to get the current value of "coupon"
    public Long getCouponValue() {
        Object value = redisTemplate.opsForValue().get("coupon");
        return value != null ? Long.parseLong(value.toString()) : 0L;
    }



}
