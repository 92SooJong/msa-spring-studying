package com.example.display.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.display.config.OpenFeignConfig;
import com.example.display.dto.AvailableProductResponseDTO;
import feign.Feign;
import feign.Headers;
import feign.RequestLine;

@FeignClient(name = "product",url = "localhost:8080",
    configuration = OpenFeignConfig.class)
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET , value = "/api/v1/available-products")
    //@Headers("Content-Type: application/xml")
    //@RequestLine(value = "GET /api/v1/available-products")
    List<AvailableProductResponseDTO> selectAvailableProducts();


}
