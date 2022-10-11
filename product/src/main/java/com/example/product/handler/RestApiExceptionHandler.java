package com.example.product.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.example.product.dto.RestApiExceptionResponseDTO;
import com.example.product.exception.RequestParamNotValidException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(annotations = {RestController.class})
@Order(value = Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class RestApiExceptionHandler {


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {
        RequestParamNotValidException.class,
        MissingServletRequestParameterException.class
    })
    public RestApiExceptionResponseDTO handleBadRequestException(Exception ex, WebRequest webRequest) {

        log.info(ex.toString());
        log.info(webRequest.toString());

//        final Locale locale = getLocale(webRequest);
//        String message = messageSource.getMessage("error.bindException.default", null, locale);
//        String userMessage = messageSource.getMessage("error.userMessage.default", null, locale);
//
//        webRequest.setAttribute(RestContollerExceptionConst.MESSAGE_KEY, message, RequestAttributes.SCOPE_REQUEST);
//        webRequest.setAttribute(RestContollerExceptionConst.USER_MESSAGE_KEY, userMessage, RequestAttributes.SCOPE_REQUEST);
//
        return new RestApiExceptionResponseDTO("default" , null,"default usermessage");
    }



}
