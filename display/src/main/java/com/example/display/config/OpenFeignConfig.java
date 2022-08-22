package com.example.display.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;
import org.springframework.http.codec.xml.Jaxb2XmlDecoder;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Contract;
import feign.Contract.Default;
import feign.Logger;
import feign.RequestTemplate;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxb.JAXBContextFactory;
import feign.jaxb.JAXBDecoder;
import feign.jaxb.JAXBEncoder;

@Configuration
public class OpenFeignConfig {


    @Bean
    public Contract feignContract(){
        return new SpringMvcContract();
        //return new Contract.Default();
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    @Bean
    public Encoder encoder(){
        //return new feign.form.FormEncoder();
        //return new JacksonEncoder();
        //return new SpringEncoder(messageConverters);
        return new GsonEncoder();
//        return new JAXBEncoder(new JAXBContextFactory.Builder()
//            .withMarshallerJAXBEncoding("UTF-8")
//            .build());


    }

    @Bean
    public Decoder decoder(){
//        return new JAXBDecoder(new JAXBContextFactory.Builder()
//            .withMarshallerJAXBEncoding("UTF-8")
//            .build());

        return new GsonDecoder();
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
