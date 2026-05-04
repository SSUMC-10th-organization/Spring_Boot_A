package org.example.umc10th_m4.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MissionResponseDto {
    private long missionId;
    private String storeName;
    private String detail;
    private int point;
    private String status;
    private int count;
}
