package com.example.display.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.display.dto.AvailableProductResponseDTO;
import com.example.display.service.DisplayService;
import com.example.display.vo.SectionVo;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DisplayController {

    private final DisplayService displayService;

    @GetMapping("/api/v1/available-products")
    public List<AvailableProductResponseDTO> selectAvailableProducts(){

        return displayService.selectAvailableProducts();
    }

    @GetMapping("/api/v1/main-section")
    public List<SectionVo> selectProducts(){
        return displayService.selectMainSections();
    }

}
