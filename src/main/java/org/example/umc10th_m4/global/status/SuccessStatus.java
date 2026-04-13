package org.example.umc10th_m4.global.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseStatus{

    OK(HttpStatus.OK, "COMMON200", "요청에 성공하였습니다."),

    CREATED(HttpStatus.CREATED, "COMMON201", "성공적으로 생성되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
