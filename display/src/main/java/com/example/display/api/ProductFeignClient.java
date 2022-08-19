package com.example.display.api;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.display.dto.AvailableProductResponseDTO;

@FeignClient(name = "product",url = "localhost:8080")
public interface ProductFeignClient {

    @RequestMapping(method = RequestMethod.GET , value = "/api/v1/available-products")
    List<AvailableProductResponseDTO> selectAvailableProducts();


}
