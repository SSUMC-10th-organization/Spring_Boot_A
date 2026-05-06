package com.example.umc.domain.user.converter;

import com.example.umc.domain.user.dto.UserResponseDTO;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.userMission.entity.UserMission;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserConverter {

    private UserConverter() {
    }

    public static UserResponseDTO.MyPageResponse toMyPageResponse(User user) {
        return UserResponseDTO.MyPageResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .birthDate(user.getBirthDate())
                .gender(user.getGender() == null ? null : user.getGender().name())
                .address(user.getAddress())
                .points(user.getPoints())
                .build();
    }

    public static UserResponseDTO.UserMissionPreviewListResponse toUserMissionPreviewListResponse(
            Page<UserMission> userMissions
    ) {
        List<UserResponseDTO.UserMissionPreviewResponse> missions = userMissions.stream()
                .map(UserConverter::toUserMissionPreviewResponse)
                .toList();

        return UserResponseDTO.UserMissionPreviewListResponse.builder()
                .missions(missions)
                .listSize(missions.size())
                .totalPage(userMissions.getTotalPages())
                .totalElements(userMissions.getTotalElements())
                .isFirst(userMissions.isFirst())
                .isLast(userMissions.isLast())
                .build();
    }

    private static UserResponseDTO.UserMissionPreviewResponse toUserMissionPreviewResponse(UserMission userMission) {
        return UserResponseDTO.UserMissionPreviewResponse.builder()
                .userMissionId(userMission.getId())
                .missionId(userMission.getMission().getId())
                .missionTitle(userMission.getMission().getTitle())
                .storeName(userMission.getMission().getStore().getName())
                .rewardPoints(userMission.getMission().getRewardPoints())
                .deadline(userMission.getMission().getDeadline())
                .status(userMission.getStatus())
                .acceptedAt(userMission.getAcceptedAt())
                .completedAt(userMission.getCompletedAt())
                .build();
    }
}
