package com.example.umc.domain.user.dto;

import com.example.umc.domain.userMission.entity.UserMissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class UserResponseDTO {

    private UserResponseDTO() {
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageResponse {
        private String name;
        private String email;
        private LocalDate birthDate;
        private String gender;
        private String address;
        private Integer points;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreviewListResponse {
        private List<UserMissionPreviewResponse> missions;
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
    public static class UserMissionPreviewResponse {
        private Long userMissionId;
        private Long missionId;
        private String missionTitle;
        private String storeName;
        private Integer rewardPoints;
        private LocalDate deadline;
        private UserMissionStatus status;
        private LocalDateTime acceptedAt;
        private LocalDateTime completedAt;
    }
}
