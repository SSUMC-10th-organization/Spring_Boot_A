package org.example.umc10th_m4.domain.region.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.umc10th_m4.global.status.BaseStatus;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum RegionErrorStatus implements BaseStatus {

    REGION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404", "존재하지 않는 지역입니다");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
