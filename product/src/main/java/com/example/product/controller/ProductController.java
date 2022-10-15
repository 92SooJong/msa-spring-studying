package com.example.product.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.product.annotation.ValidCountryCode;
import com.example.product.constant.CountryCodeEnum;
import com.example.product.dto.ProductResponseDTO;
import com.example.product.exception.RequestParamNotValidException;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;
@Validated
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * SAMPLE API FOR TESTING FEIGN CLIENT
     */
    @GetMapping("/product/v1")
    public List<ProductResponseDTO> selectAvailableProducts() throws InterruptedException {
        return productService.selectAvailableProducts();
    }

    /**
     * SAMPLE API FRO TESTING EXCEPTION HANDLER
     */
    @GetMapping("/product/v1/{productNo}")
    public ProductResponseDTO getProductResponseDTO(@PathVariable Long productNo, @RequestParam Long productGroupNo) {

        if(productGroupNo == null) throw new RequestParamNotValidException("알 수 없는 에러가 발생했습니다");

        return productService.getProductResponseDTO(productNo,productGroupNo);
    }


    @GetMapping("/product/v1/validation-country")
    public String validationTest(@RequestParam @ValidCountryCode String countryCode) {

        return CountryCodeEnum.valueOf(countryCode).toString();

    }




}
