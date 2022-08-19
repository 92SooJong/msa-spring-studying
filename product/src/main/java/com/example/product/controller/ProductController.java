package com.example.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import com.example.product.dto.ProductResponseDTO;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    public List<ProductResponseDTO> selectAvailableProducts(){

        return productService.selectAvailableProducts();
    }





}
