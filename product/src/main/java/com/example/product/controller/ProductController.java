package com.example.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.product.dto.ProductResponseDTO;
import com.example.product.exception.RequestParamNotValidException;
import com.example.product.service.ProductService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    /**
     * 구매 가능한 제품목록을 모두 조회한다.
     * @return 제품정보 목록
     */
    @GetMapping("/product/v1")
    public List<ProductResponseDTO> selectAvailableProducts() throws InterruptedException {
        return productService.selectAvailableProducts();
    }

    @GetMapping("/product/v1/{productNo}")
    public ProductResponseDTO getProductResponseDTO(@PathVariable Long productNo, @RequestParam Long productGroupNo) {


        if(productGroupNo == null) throw new RequestParamNotValidException("알 수 없는 에러가 발생했습니다");


        return productService.getProductResponseDTO(productNo,productGroupNo);
    }




}
