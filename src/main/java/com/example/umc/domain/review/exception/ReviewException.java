package com.example.umc.domain.review.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {

    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
