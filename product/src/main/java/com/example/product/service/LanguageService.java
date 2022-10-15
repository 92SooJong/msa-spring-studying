package com.example.product.service;

import java.util.EnumMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.example.product.constant.LanguageCodeEnum;

@Service
public class LanguageService {


    public void test(){

        Map<LanguageCodeEnum, String> map = new EnumMap<>(LanguageCodeEnum.class);
        map.put(LanguageCodeEnum.ENGLISH, "영어입니다.");

    }

}
