package org.example.domain;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

@Service
public class RedisQueueService {

    private final RedisTemplate<String, Integer> redisTemplate;
    private final Random random;


    private static final String QUEUE_NAME = "couponQueue";
    private static final long MAX_SIZE = 50;
    private static final int MIN_RANDOM = 1;
    private static final int MAX_RANDOM = 8;


    public RedisQueueService(RedisTemplate<String, Integer> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.random = new Random();
    }

    // Enqueue method with size limit
    public void enqueue(Integer item) {
        redisTemplate.opsForList().rightPush(QUEUE_NAME, item);
        redisTemplate.opsForList().trim(QUEUE_NAME, -MAX_SIZE, -1);
    }

    // Dequeue method
    public Integer dequeue() {
        return (Integer) redisTemplate.opsForList().leftPop(QUEUE_NAME);
    }

    // Method to get the current size of the queue
    public Long getQueueSize() {
        return redisTemplate.opsForList().size(QUEUE_NAME);
    }

    // Method to enqueue a random number between 1 and 8
    public void enqueueRandomNumber() {
        int randomNumber = MIN_RANDOM + random.nextInt(MAX_RANDOM - MIN_RANDOM + 1);
        enqueue(randomNumber);
        System.out.println("Enqueued Random Number: " + randomNumber);
    }

    // Method to fill the queue with random numbers up to MAX_SIZE
    public void fillQueueWithRandomNumbers() {
        long currentSize = getQueueSize();
        long itemsToAdd = MAX_SIZE - currentSize;

        for (int i = 0; i < itemsToAdd; i++) {
            enqueueRandomNumber();
        }

        System.out.println("Queue filled with random numbers. Current size: " + getQueueSize());
    }



}
