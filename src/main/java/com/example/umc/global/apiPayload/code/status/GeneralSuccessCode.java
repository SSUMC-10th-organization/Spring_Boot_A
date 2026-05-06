package com.example.umc.global.apiPayload.code.status;

import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import com.example.umc.global.apiPayload.code.ReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GeneralSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "COMMON200", "성공입니다."),
    CREATED(HttpStatus.CREATED, "COMMON201", "생성에 성공했습니다.");

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
