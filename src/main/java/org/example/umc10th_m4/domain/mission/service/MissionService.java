package org.example.umc10th_m4.domain.mission.service;

import org.example.umc10th_m4.domain.mission.dto.MissionResponseDto;

import java.util.List;

public interface MissionService {
    List<MissionResponseDto> getMissionsByRegion(String regionName, int page);
    List<MissionResponseDto> getMyMissions(long memberId, String status, int page);
}
