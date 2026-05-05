package com.example.umc10th.global.handler;

import com.example.umc10th.global.exception.GeneralException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(GeneralException.class)
    public ApiResponse<?> handleGeneralException(GeneralException e) {
        return ApiResponse.onFailure(e.getCode(), null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception e) {
        return ApiResponse.onFailure(GeneralErrorCode.INTERNAL_ERROR, null);
    }
}