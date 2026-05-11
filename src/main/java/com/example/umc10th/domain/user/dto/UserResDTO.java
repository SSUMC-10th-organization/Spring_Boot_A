package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class UserResDTO {

    // 마이페이지
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MyPageRes {
        private String nickname;
        private String email;
        private String phoneNum;
        private Integer point;
    }

    // 기존 컨트롤러 호환용
    @Getter
    @AllArgsConstructor
    public static class UserInfoRes {
        private Long userId;
        private String nickname;
        private Integer point;
    }

    @Getter
    @AllArgsConstructor
    public static class MissionProgressRes {
        private Integer totalMissions;
        private Integer successMissions;
        private Integer ongoingMissions;
    }

    @Getter
    @AllArgsConstructor
    public static class MissionRes {
        private Long missionId;
        private String title;
        private Integer point;
        private MissionStatus status;
    }

    @Getter
    @AllArgsConstructor
    public static class MissionChallengeRes {
        private Long userMissionId;
        private MissionStatus status;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    public static class JoinRes {
        private Long userId;
        private String email;
    }
}
