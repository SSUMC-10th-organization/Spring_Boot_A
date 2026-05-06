package com.example.umc.domain.user.exception;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.exception.ProjectException;

public class UserException extends ProjectException {

    public UserException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
