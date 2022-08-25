package com.example.display.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("default")
class DisplayControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private CircuitBreakerRegistry circuitBreakerRegistry;

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

        List<Thread> threads = new ArrayList<>();
        for(int i=0; i<10; i++){
            Thread thread = new Thread(new CallAvailableProductsAPI(i,mockMvc,url));
            System.out.printf("%d번째 쓰레드 시작 \n", i+1);
            thread.start();
            //thread.join();
            threads.add(thread);
            System.out.printf("Sliding Window에 들어있는 느린 요청 수 = %d \n\n" ,
                circuitBreakerRegistry.circuitBreaker("cb-product-feign").getMetrics().getNumberOfSlowCalls());
        }

        for(Thread t: threads) t.join();

        System.out.printf("Sliding Window에 들어있는 느린 요청 수 = %d \n\n" ,
            circuitBreakerRegistry.circuitBreaker("cb-product-feign").getMetrics().getNumberOfSlowCalls());
    }
}
