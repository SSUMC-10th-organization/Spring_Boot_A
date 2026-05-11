package org.example.umc10th_m4.domain.mission.service;

import org.example.umc10th_m4.domain.mission.dto.MissionResponseDto;
import org.example.umc10th_m4.domain.mission.dto.MyMissionRequestDto;
import org.example.umc10th_m4.global.common.PageResponse;

import java.util.List;

public interface MissionService {
    List<MissionResponseDto> getMissionsByRegion(String regionName, int page);
    PageResponse<MissionResponseDto> getMyOngoingMissions(MyMissionRequestDto request, int page, int pageSize);
}
