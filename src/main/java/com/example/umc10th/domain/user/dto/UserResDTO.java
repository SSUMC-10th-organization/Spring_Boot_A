package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.List;

public class UserResDTO {

    @Getter
    @AllArgsConstructor
    public static class UserInfoRes {
        private Long userId;
        private String name;
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
    @AllArgsConstructor
    public static class JoinRes {
        private Long userId;
        private String name;
        private String email;
    }
}