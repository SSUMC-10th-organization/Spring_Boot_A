package org.example.umc10th_m4.domain.mission.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.umc10th_m4.global.status.BaseStatus;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum MissionErrorStatus implements BaseStatus {

    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404", "존재하지 않는 미션입니다");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
