package com.example.umc10th.domain.mission.converter;

import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;

import java.util.List;

public class MissionConverter {

    public static MemberMissionResDTO.OngoingMissionItem toOngoingMissionItem(UserMission userMission) {
        return MemberMissionResDTO.OngoingMissionItem.builder()
                .missionId(userMission.getMission().getId())
                .point(userMission.getMission().getRewardPoint())
                .conditional(userMission.getMission().getDescription())
                .deadline(null)
                .build();
    }

    public static <T> MissionResDTO.OffsetPaginationRes<T> toPagination(List<T> data, Integer pageNumber, Integer pageSize) {
        return MissionResDTO.OffsetPaginationRes.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
