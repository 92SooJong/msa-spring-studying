package com.example.display.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.display.api.ProductFeignClient;
import com.example.display.dto.AvailableProductResponseDTO;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisplayService {

    private final ProductFeignClient productFeignClient;
    private final TimeLimiterRegistry timeLimiterRegistry;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    public List<AvailableProductResponseDTO> selectAvailableProducts(){

//        System.out.println("timeLimiterRegistry.getTimeoutDuration = " + timeLimiterRegistry.getDefaultConfig().getTimeoutDuration());
//        System.out.println("circuitBreakerRegistry.getMinimumNumberOfCalls = " + circuitBreakerRegistry.circuitBreaker("cb-product-feign").getCircuitBreakerConfig().getMinimumNumberOfCalls());
//        System.out.println("circuitBreakerRegistry = " + circuitBreakerRegistry.circuitBreaker("cb-product-feign").getCircuitBreakerConfig().toString());
//
//        System.out.println("productFeignClient.getAllCircuitBreakers = " + circuitBreakerRegistry.getAllCircuitBreakers().toString());


        List<AvailableProductResponseDTO> result = productFeignClient.selectAvailableProducts();
        //System.out.printf("Sliding Window에 들어있는 느린 요청 수 = %d \n" , circuitBreakerRegistry.circuitBreaker("cb-product-feign").getMetrics().getNumberOfSlowCalls());

        //System.out.println("result.toString() = " + result.toString());
        return result;
    }

}
