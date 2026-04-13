package com.example.umc10th.domain.restaurant.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import com.example.umc10th.global.apiPayload.exception.ProjectException;

public class RestaurantException extends ProjectException {
    public RestaurantException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}