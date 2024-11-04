package org.example.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.example.domain.RedisQueueService;
import org.example.domain.RedisService;

@RestController
@RequestMapping("/api/redis")
public class RedisTestController {

    private final RedisService redisService;
    private final RedisQueueService redisQueueService;

    public RedisTestController(RedisService redisService, RedisQueueService redisQueueService) {
        this.redisService = redisService;
        this.redisQueueService = redisQueueService;
    }

    // Endpoint to set a value in Redis
    @PostMapping("/set")
    public String setValue(@RequestParam String key, @RequestParam Integer value) {
        redisService.saveValue(key, value);
        return "Value saved successfully";
    }

    // Endpoint to get a value from Redis
    @GetMapping("/get")
    public String getValue(@RequestParam String key) {
        Integer value = redisService.getValue(key);
        return value != null ? value.toString() : "Value not found";
    }

    @PostMapping("/increment-coupon")
    public String incrementCoupon() {
        return redisService.incrementCouponWithLock();
    }

    @PostMapping("/reset-queue")
    public void resetQueue() {
        redisQueueService.fillQueueWithRandomNumbers();
    }


    @GetMapping("/dequeue")
    public String dequeue(@RequestParam String requestNumber) {
        Integer dequeue = redisQueueService.dequeue();
        return "User : " + requestNumber + " Dequeue Random Number: " + dequeue + "\n";

    }



}
