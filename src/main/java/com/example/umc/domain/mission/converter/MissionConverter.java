package com.example.umc.domain.mission.converter;

import com.example.umc.domain.mission.dto.MissionResponseDTO;
import com.example.umc.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;

import java.util.List;

public class MissionConverter {

    private MissionConverter() {
    }

    public static MissionResponseDTO.MissionPreviewListResponse toMissionPreviewListResponse(Page<Mission> missions) {
        List<MissionResponseDTO.MissionPreviewResponse> missionList = missions.stream()
                .map(MissionConverter::toMissionPreviewResponse)
                .toList();

        return MissionResponseDTO.MissionPreviewListResponse.builder()
                .missions(missionList)
                .listSize(missionList.size())
                .totalPage(missions.getTotalPages())
                .totalElements(missions.getTotalElements())
                .isFirst(missions.isFirst())
                .isLast(missions.isLast())
                .build();
    }

    private static MissionResponseDTO.MissionPreviewResponse toMissionPreviewResponse(Mission mission) {
        return MissionResponseDTO.MissionPreviewResponse.builder()
                .missionId(mission.getId())
                .title(mission.getTitle())
                .rewardPoints(mission.getRewardPoints())
                .deadline(mission.getDeadline())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .storeAddress(mission.getStore().getAddress())
                .categoryName(mission.getStore().getCategory().getName())
                .build();
    }
}
