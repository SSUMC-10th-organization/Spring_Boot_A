package com.example.umc10th.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    // 진행중인 미션 조회 요청
    public record GetMyMissions(
            @NotNull(message = "회원 ID는 필수입니다.")
            Long memberId
    ) {}
}