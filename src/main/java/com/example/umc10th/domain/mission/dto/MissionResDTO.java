package com.example.umc10th.domain.mission.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MissionResDTO {

    // 기존 유지
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

    // 오프셋 기반 페이지네이션 응답으로 변경
    public record MissionListResult(
            List<MissionDetail> missionList,
            Integer pageNumber,
            Integer pageSize,
            Long totalCount,
            Integer totalPages,
            Boolean hasNext
    ) {}
}