package com.example.product.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

public class RestApiExceptionResponseDTO {

    private String code;
    private String message;
    private String userMessage;

    public RestApiExceptionResponseDTO(String code, String message, String userMessage) {
        this.code = code;
        this.message = message == null ? "에러가 발생했습니다." : message;
        this.userMessage = userMessage;
    }
}
