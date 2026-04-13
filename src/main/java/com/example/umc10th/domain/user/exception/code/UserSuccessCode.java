package com.example.umc10th.domain.user.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    USER_INFO_OK(HttpStatus.OK, "200_0", "사용자 정보 조회 성공"),
    USER_MISSION_PROGRESS_OK(HttpStatus.OK, "200_1", "미션 진행 현황 조회 성공"),
    USER_MISSIONS_OK(HttpStatus.OK, "200_2", "미션 목록 조회 성공"),
    USER_MISSION_CHALLENGE_CREATED(HttpStatus.CREATED, "200_3", "미션 도전 성공"),
    USER_MISSION_SUCCESS_OK(HttpStatus.OK, "200_4", "미션 성공 처리 완료"),
    USER_JOIN_CREATED(HttpStatus.CREATED, "201_0", "회원가입 성공");

    private final HttpStatus status;
    private final String code;
    private final String message;
}