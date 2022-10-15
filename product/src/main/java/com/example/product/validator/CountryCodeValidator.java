package com.example.product.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;
import com.example.product.annotation.ValidCountryCode;
import com.example.product.constant.CountryCodeEnum;

@Component
public class CountryCodeValidator implements ConstraintValidator<ValidCountryCode, String> {


    @Override
    public void initialize(ValidCountryCode constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String code, ConstraintValidatorContext context) {

        try {
            CountryCodeEnum.valueOf(code);
        } catch (Exception e) {
            return false;
        }
        return true;

    }
}
