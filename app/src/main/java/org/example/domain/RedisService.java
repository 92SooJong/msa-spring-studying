package org.example.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    // Method to increment the value of "coupon" by 1
    public Long incrementCoupon() {
        return redisTemplate.opsForValue().increment("coupon");
    }

    // Method to get the current value of "coupon"
    public Long getCouponValue() {
        Object value = redisTemplate.opsForValue().get("coupon");
        return value != null ? Long.parseLong(value.toString()) : 0L;
    }

}
