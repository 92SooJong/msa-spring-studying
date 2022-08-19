package com.example.display.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class AvailableProductResponseDTO {

    private Long productId;
    private String productName;
    private int productStockCount;

}
