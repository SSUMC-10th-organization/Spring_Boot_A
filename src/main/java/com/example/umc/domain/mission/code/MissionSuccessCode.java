package com.example.umc.domain.mission.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import com.example.umc.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    AVAILABLE_MISSIONS_FOUND(HttpStatus.OK, "MISSION200_1", "성공적으로 도전 가능한 미션 목록을 조회했습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDTO getReason() {
        return new ReasonDTO(null, true, code, message);
    }

    @Override
    public ReasonDTO getReasonHttpStatus() {
        return new ReasonDTO(httpStatus, true, code, message);
    }
}
