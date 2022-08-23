package com.example.display.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.display.api.ProductFeignClient.ProductCircuitBreaker;
import com.example.display.config.CircuitBreakerConfig;
import com.example.display.config.OpenFeignConfig;
import com.example.display.dto.AvailableProductResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;


@FeignClient(name = "product",url = "localhost:8080",
    configuration = {OpenFeignConfig.class, CircuitBreakerConfig.class},fallback = ProductCircuitBreaker.class)
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET ,
        value = "/api/v1/available-products")
    List<AvailableProductResponseDTO> selectAvailableProducts();

    @Component
    static class ProductCircuitBreaker implements ProductFeignClient{

        @Override
        public List<AvailableProductResponseDTO> selectAvailableProducts() {
            System.out.println(" Circuit Breaker작동 ");
            return new ArrayList<>();
        }
    }
}

//@Headers("Content-Type: application/xml")
//@RequestLine(value = "GET /api/v1/available-products")