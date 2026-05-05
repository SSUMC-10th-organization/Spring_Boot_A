package com.example.umc10th.global.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {

    _OK("COMMON200", "성공입니다");

    private final String code;
    private final String message;
}