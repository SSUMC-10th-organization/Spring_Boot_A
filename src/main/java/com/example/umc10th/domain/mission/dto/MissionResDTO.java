package com.example.demo.mission.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    public record MissionListResult(
            List<MissionDetail> missionList,
            Integer totalCount
    ) {}

    public record MissionDetail(
            Long missionId,
            String storeName,
            String missionContent,
            Integer reward,
            String status
    ) {}

    public record MissionCompleteResult(
            Long missionId,
            String status,
            LocalDateTime completedAt
    ) {}
}
