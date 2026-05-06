package com.example.umc10th.domain.mission.exception;

import com.example.umc10th.global.handler.BaseErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    MISSION_NOT_FOUND(404, "MISSION404", "존재하지 않는 미션입니다"),
    MISSION_ALREADY_COMPLETE(400, "MISSION400", "이미 완료된 미션입니다");

    private final int status;
    private final String code;
    private final String message;
}