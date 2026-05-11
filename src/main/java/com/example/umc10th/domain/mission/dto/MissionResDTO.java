package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class MissionResDTO {

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionItem {
        private String restaurantName;
        private Integer rewardPoint;
        private MissionStatus status;
        private String description;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyMissionListRes {
        private List<MyMissionItem> missions;
        private boolean hasNext;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeMissionItem {
        private String restaurantName;
        private String description;
        private Integer rewardPoint;
        private Long successCount;
        private Long totalCount;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class HomeMissionListRes {
        private List<HomeMissionItem> missions;
        private boolean hasNext;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class OffsetPaginationRes<T> {
        private List<T> data;
        private Integer pageNumber;
        private Integer pageSize;
    }
}
