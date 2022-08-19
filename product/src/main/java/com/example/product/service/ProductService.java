package com.example.product.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.example.product.dto.ProductResponseDTO;

@Service
public class ProductService {

    public List<ProductResponseDTO> selectAvailableProducts(){

        List<ProductResponseDTO> result = new ArrayList<>();
        result.add(ProductResponseDTO.builder()
            .productId(1L)
            .productName("Product-A")
            .productStockCount(100)
            .expiredAt(LocalDateTime.now().plusMonths(2))
            .build());

        result.add(ProductResponseDTO.builder()
            .productId(2L)
            .productName("Product-B")
            .productStockCount(1)
            .expiredAt(LocalDateTime.now().minusMonths(1))
            .build());

        result.add(ProductResponseDTO.builder()
            .productId(3L)
            .productName("Product-C")
            .productStockCount(20)
            .expiredAt(LocalDateTime.now().plusMonths(1))
            .build());

        return result;

    }

}
