package com.example.display.config;

import java.time.Duration;

import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;
import io.github.resilience4j.common.timelimiter.configuration.TimeLimiterConfigCustomizer;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;

@Configuration
public class Resilience4jCircuitBreakerConfig {


    //@Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer() {


        CircuitBreakerConfig build = CircuitBreakerConfig.custom()
            .slidingWindowSize(5)
            .slidingWindowType(SlidingWindowType.COUNT_BASED)
            .slowCallDurationThreshold(Duration.ofMillis(2000))
            .failureRateThreshold(10)
            .slowCallRateThreshold(10)
            .minimumNumberOfCalls(5)
            .build();


        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(build)
            .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(10)).build()), "cb-product-feign");

    }

    //@Bean
    public Customizer<Resilience4JCircuitBreakerFactory> defaultCustomizer22() {

        CircuitBreakerConfig build = CircuitBreakerConfig.custom()
            .slidingWindowSize(5)
            .slidingWindowType(SlidingWindowType.COUNT_BASED)
            .slowCallDurationThreshold(Duration.ofMillis(2000))
            .failureRateThreshold(10)
            .slowCallRateThreshold(10)
            .minimumNumberOfCalls(5)
            .build();


        return factory -> factory.configure(builder -> builder.circuitBreakerConfig(build)
            .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(5)).build()), "product-feign");

    }


    //@Bean
    public CircuitBreakerConfigCustomizer testCustomizer() {
        return CircuitBreakerConfigCustomizer
            .of("cb-product-feign", builder -> builder
                .slidingWindowSize(5)
                .slidingWindowType(SlidingWindowType.COUNT_BASED)
                .slowCallDurationThreshold(Duration.ofMillis(2000))
                .failureRateThreshold(10)
                .slowCallRateThreshold(10)
                .minimumNumberOfCalls(5)
                .build());
    }

    //@Bean
    public TimeLimiterConfigCustomizer timeLimiterConfigCustomizer(){
        return TimeLimiterConfigCustomizer.of("cb-product-feign",builder -> builder
            .timeoutDuration(Duration.ofSeconds(100))
            .cancelRunningFuture(false)
            .build());
    }







}
