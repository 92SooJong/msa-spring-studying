package com.example.product.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Builder
public class ProductResponseDTO {

    private Long productId;
    private String productName;
    private int productStockCount;
    private LocalDateTime expiredAt;

}
