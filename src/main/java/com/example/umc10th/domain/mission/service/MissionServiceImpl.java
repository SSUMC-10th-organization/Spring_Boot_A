package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.mapping.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private static final int PAGE_SIZE = 10;

    private final UserMissionRepository userMissionRepository;

    @Override
    public MissionResDTO.MyMissionListRes getMyMissions(Long userId, MissionStatus status, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<UserMission> result = userMissionRepository.findByUserIdAndStatus(userId, status, pageable);

        List<MissionResDTO.MyMissionItem> items = result.getContent().stream()
                .map(um -> MissionResDTO.MyMissionItem.builder()
                        .restaurantName(um.getMission().getRestaurant().getName())
                        .rewardPoint(um.getMission().getRewardPoint())
                        .status(um.getStatus())
                        .description(um.getMission().getDescription())
                        .build())
                .toList();

        return MissionResDTO.MyMissionListRes.builder()
                .missions(items)
                .hasNext(result.hasNext())
                .build();
    }

    @Override
    public MissionResDTO.HomeMissionListRes getHomeMissions(Long userId, Long locationId, int page) {
        Pageable pageable = PageRequest.of(page, PAGE_SIZE);
        Page<Mission> result = userMissionRepository.findAvailableMissions(userId, locationId, pageable);

        List<MissionResDTO.HomeMissionItem> items = result.getContent().stream()
                .map(m -> MissionResDTO.HomeMissionItem.builder()
                        .restaurantName(m.getRestaurant().getName())
                        .description(m.getDescription())
                        .rewardPoint(m.getRewardPoint())
                        .successCount(0L)
                        .totalCount(0L)
                        .build())
                .toList();

        return MissionResDTO.HomeMissionListRes.builder()
                .missions(items)
                .hasNext(result.hasNext())
                .build();
    }
}
