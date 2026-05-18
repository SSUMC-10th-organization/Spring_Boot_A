package com.example.umc.domain.user.code;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import com.example.umc.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserSuccessCode implements BaseSuccessCode {

    USER_FOUND(HttpStatus.OK, "USER200_1", "성공적으로 유저를 조회했습니다."),
    USER_MISSIONS_FOUND(HttpStatus.OK, "USER200_2", "성공적으로 유저 미션 목록을 조회했습니다."),
    IN_PROGRESS_MISSIONS_FOUND(HttpStatus.OK, "USER200_3", "성공적으로 진행중인 미션 목록을 조회했습니다.");

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
