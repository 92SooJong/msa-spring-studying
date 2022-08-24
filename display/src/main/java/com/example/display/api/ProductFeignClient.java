package com.example.display.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cloud.client.circuitbreaker.NoFallbackAvailableException;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.display.api.ProductFeignClient.ProductFeignFallback;
import com.example.display.config.OpenFeignConfig;
import com.example.display.dto.AvailableProductResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;

@FeignClient(name = "product-feign",url = "localhost:8080",
    configuration = OpenFeignConfig.class, fallback = ProductFeignFallback.class)
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET ,
        value = "/product/api/v1/available-products")
    List<AvailableProductResponseDTO> selectAvailableProducts();

    @Component
    @Slf4j
    class ProductFeignFallback implements ProductFeignClient{

        @Override
        public List<AvailableProductResponseDTO> selectAvailableProducts() {
            log.debug("Circuit is opened!");
            System.out.println(" Circuit is OPENED!!!!!");
            return new ArrayList<>();
        }
    }
}
