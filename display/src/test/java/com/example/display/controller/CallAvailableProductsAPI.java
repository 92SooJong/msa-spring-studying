package com.example.display.controller;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CallAvailableProductsAPI implements Runnable{

    private MockMvc mockMvc;
    private String url;
    private int count;
    public CallAvailableProductsAPI(int count, MockMvc mockMvc, String url) {
        this.count = count;
        this.mockMvc = mockMvc;
        this.url = url;
    }

    @Override
    public void run() {
        try {
            mockMvc.perform(get(url)).andExpect(status().isOk());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
