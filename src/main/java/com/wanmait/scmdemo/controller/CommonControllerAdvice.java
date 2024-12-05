package com.wanmait.scmdemo.controller;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wanmait.scmdemo.util.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;


@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler(JWTVerificationException.class)
    public AjaxResult processJWTVerificationException(JWTVerificationException e){
        return AjaxResult.error(10001,e.getMessage());
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public AjaxResult processMaxUploadSizeExceededException(MaxUploadSizeExceededException e){
        return AjaxResult.error(10005,e.getMessage());
    }
}
