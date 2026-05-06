package com.example.umc.domain.mission.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDTO {

    private MissionResponseDTO() {
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewListResponse {
        private List<MissionPreviewResponse> missions;
        private Integer listSize;
        private Integer totalPage;
        private Long totalElements;
        private Boolean isFirst;
        private Boolean isLast;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPreviewResponse {
        private Long missionId;
        private String title;
        private Integer rewardPoints;
        private LocalDate deadline;
        private Long storeId;
        private String storeName;
        private String storeAddress;
        private String categoryName;
    }
}
