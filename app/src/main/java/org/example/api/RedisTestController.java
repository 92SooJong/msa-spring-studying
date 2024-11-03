package org.example.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.domain.RedisService;

@RestController
@RequestMapping("/api/redis")
public class RedisTestController {

    private final RedisService redisService;

    public RedisTestController(RedisService redisService) {
        this.redisService = redisService;
    }

    // Endpoint to set a value in Redis
    @PostMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam String value) {
        redisService.saveValue(key, value);
        return "Value saved successfully";
    }

    // Endpoint to get a value from Redis
    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        String value = redisService.getValue(key);
        return value != null ? value : "Value not found";
    }

    @PostMapping("/increment-coupon")
    public String incrementCoupon() {
        return redisService.incrementCouponWithLock();
    }


}
