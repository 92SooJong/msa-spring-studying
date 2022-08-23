package com.example.display.config;


import java.lang.reflect.Method;

import org.springframework.cloud.openfeign.CircuitBreakerNameResolver;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.Contract;
import feign.Logger;

import feign.Logger.Level;
import feign.Target;
import feign.Util;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class OpenFeignConfig {


    @Bean
    public CircuitBreakerNameResolver circuitBreakerNameResolver(){
        // Naming Rule : "cb_[Feign 이름]"
        return (String feignClientName, Target<?> target, Method method) -> "cb-"+feignClientName;
    }

    @Bean
    public Contract feignContract(){
        return new SpringMvcContract();
        //return new Contract.Default();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Level.NONE;
    }


    @Bean
    public Encoder encoder(){
        //return new feign.form.FormEncoder();
        //return new JacksonEncoder();
        //return new SpringEncoder(messageConverters);
        return new JacksonEncoder();


    }

    @Bean
    public Decoder decoder(){

        return (response, type) -> {
            String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
            //log.info("bodyStr>>>" + bodyStr);
            JavaType javaType = TypeFactory.defaultInstance().constructType(type);
            System.out.println("bodyStr = " + bodyStr);
            return new ObjectMapper().readValue( bodyStr, javaType);
        };


        //return new GsonDecoder();
        //return new JacksonDecoder();
    }



//
//
//    @Bean
//    public Encoder feignEncoder() {
//
//        final ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//
//        HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter(objectMapper);
//        ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
//        return new SpringEncoder(objectFactory);
//    }


}
