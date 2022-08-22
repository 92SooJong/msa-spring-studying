package com.example.display.config;

import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import feign.Contract;
import feign.Contract.Default;

@Configuration
public class OpenFeignConfig {

    @Bean
    public Contract feignContract(){
        //return new SpringMvcContract();
        return new Contract.Default();
    }

}
