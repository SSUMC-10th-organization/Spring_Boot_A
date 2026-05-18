package org.example.umc10th_m4.domain.mission.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MyMissionRequestDto {
    @NotNull(message = "회원 ID는 필수입니다")
    private Long memberId;
}
