package org.example.domain;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

@Service
public class RedisService {

    private final RedisTemplate<String, Integer> redisTemplate;
    private final RedissonClient redissonClient;

    private static final long COUPON_LIMIT = 1000;
    private static final String LOCK_KEY = "couponLock"; // Lock key for Redisson

    private static final String QUEUE_NAME = "integerQueue";
    private static final long MAX_SIZE = 1000;
    private static final int MIN_RANDOM = 1;
    private static final int MAX_RANDOM = 8;


    public RedisService(RedisTemplate<String, Integer> redisTemplate, RedissonClient redissonClient) {
        this.redisTemplate = redisTemplate;
        this.redissonClient = redissonClient;
    }

    public void saveValue(String key, Integer value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Integer getValue(String key) {
        return redisTemplate.opsForValue().get(key);
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
                    return "Coupon limit reached. No more increments allowed.\n";
                }

                // 쿠폰 발행 API 실행... (thread sleep 1초)
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    Thread.currentThread().interrupt();
//                }

                // Increment the coupon value
                Long newValue = redisTemplate.opsForValue().increment("coupon");

                return "Coupon incremented successfully. Current value: " + newValue + "\n";
            } else {
                return "Could not acquire lock, please try again.\n";
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return "An error occurred while attempting to acquire lock.\n";
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
