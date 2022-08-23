package com.example.display.config;

import java.time.Duration;

import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;

@Configuration
public class CircuitBreakerConfig {


    @Bean
    public CircuitBreakerConfigCustomizer testCustomizer() {
        return CircuitBreakerConfigCustomizer
            .of("backendA", builder -> builder
                .slidingWindowSize(5)
                .slidingWindowType(SlidingWindowType.COUNT_BASED)
                .slowCallDurationThreshold(Duration.ofMillis(100))
                .failureRateThreshold(10)
                .slowCallRateThreshold(10)
                .minimumNumberOfCalls(5)
                .build());
    }




}
