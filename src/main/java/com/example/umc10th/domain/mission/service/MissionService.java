package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MemberMissionResDTO;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.enums.MissionStatus;

public interface MissionService {
    MissionResDTO.MyMissionListRes getMyMissions(Long userId, MissionStatus status, int page);
    MissionResDTO.HomeMissionListRes getHomeMissions(Long userId, Long locationId, int page);
    MissionResDTO.OffsetPaginationRes<MemberMissionResDTO.OngoingMissionItem> getMissions(
            Long memberId, Integer pageSize, Integer pageNumber, String sort);
}
