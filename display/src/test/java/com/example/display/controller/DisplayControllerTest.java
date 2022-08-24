package com.example.display.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DisplayControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .build();
    }


    @Test
    @DisplayName("10번 API를 요청한다.")
    void selectAvailableProductsFor10Times() throws Exception {

        String url = "http://localhost:" + port + "/api/v1/available-products";

        for(int i=1; i<11; i++){
            Thread thread = new Thread(new MyTest(i,mockMvc,url));
            thread.start();
        }
        //mockMvc.perform(get(url)).andExpect(status().isOk());



    }
}