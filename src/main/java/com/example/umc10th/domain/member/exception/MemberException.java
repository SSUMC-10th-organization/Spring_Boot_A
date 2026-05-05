package com.example.umc10th.domain.member.exception;

import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.global.exception.GeneralException;

public class MemberException extends GeneralException {
    public MemberException(MemberErrorCode code) {
        super(code);
    }
}