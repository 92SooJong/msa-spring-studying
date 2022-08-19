package com.example.display.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import com.example.display.dto.AvailableProductResponseDTO;
import com.example.display.service.DisplayService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DisplayController {

    private final DisplayService displayService;

    public List<AvailableProductResponseDTO> selectAvailableProducts(){

        return displayService.selectAvailableProducts();
    }


}
