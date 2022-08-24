package com.example.display.controller;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class MyTest implements Runnable{

    private MockMvc mockMvc;
    private String url;
    private int count;
    public MyTest(int count, MockMvc mockMvc, String url) {
        this.count = count;
        this.mockMvc = mockMvc;
        this.url = url;
    }

    @Override
    public void run() {
        System.out.println(this.count + "번째 쓰레드 시작");
        try {
            mockMvc.perform(get(url));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
