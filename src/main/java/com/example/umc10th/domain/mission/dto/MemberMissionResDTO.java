package com.example.umc10th.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

public class MemberMissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class OngoingMissionItem {
        private Long missionId;
        private Integer point;
        private String conditional;
        private LocalDate deadline;
    }
}
