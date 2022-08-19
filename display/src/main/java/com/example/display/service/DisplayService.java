package com.example.display.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.display.api.ProductFeignClient;
import com.example.display.dto.AvailableProductResponseDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DisplayService {

    private final ProductFeignClient productFeignClient;

    public List<AvailableProductResponseDTO> selectAvailableProducts(){
        return productFeignClient.selectAvailableProducts();
    }



}
