package com.example.umc.domain.mission.service;

import com.example.umc.domain.mission.converter.MissionConverter;
import com.example.umc.domain.mission.dto.MissionResponseDTO;
import com.example.umc.domain.mission.entity.Mission;
import com.example.umc.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;

    public MissionResponseDTO.MissionPreviewListResponse getAvailableMissions(String region, Integer page) {
        Page<Mission> missions = missionRepository.findAvailableMissionsByRegion(
                region,
                LocalDate.now(),
                PageRequest.of(page, 10)
        );
        return MissionConverter.toMissionPreviewListResponse(missions);
    }
}
