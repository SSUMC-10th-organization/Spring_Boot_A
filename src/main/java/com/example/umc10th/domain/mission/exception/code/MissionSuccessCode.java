package com.example.umc10th.domain.mission.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MY_MISSIONS_OK(HttpStatus.OK, "MISSION_200_0", "내 미션 목록 조회 성공"),
    HOME_MISSIONS_OK(HttpStatus.OK, "MISSION_200_1", "홈 미션 목록 조회 성공"),
    ONGOING_MISSIONS_OK(HttpStatus.OK, "MISSION_200_2", "진행중인 미션 목록 조회 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

