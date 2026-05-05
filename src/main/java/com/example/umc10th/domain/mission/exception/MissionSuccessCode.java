package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.handler.BaseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseCode {

    MISSION_SUCCESS("MISSION200", "미션 성공입니다");

    private final String code;
    private final String message;
}