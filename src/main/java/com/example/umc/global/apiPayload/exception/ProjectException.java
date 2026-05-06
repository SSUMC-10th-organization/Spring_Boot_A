package com.example.umc.global.apiPayload.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;

@Getter
public class ProjectException extends RuntimeException {

    private final BaseErrorCode errorCode;

    public ProjectException(BaseErrorCode errorCode) {
        super(errorCode.getReason().message());
        this.errorCode = errorCode;
    }
}
