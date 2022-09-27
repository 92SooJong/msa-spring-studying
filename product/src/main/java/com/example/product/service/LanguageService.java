package com.example.product.service;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.example.product.constant.LanguageType;

@Service
public class LanguageService {


    public void test(){

        Map<LanguageType, String> map = new EnumMap<>(LanguageType.class);
        map.put(LanguageType.ENGLISH, "영어입니다.");

    }

}
