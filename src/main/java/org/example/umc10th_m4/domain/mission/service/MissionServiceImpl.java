package org.example.umc10th_m4.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.mission.dto.MissionResponseDto;
import org.example.umc10th_m4.domain.mission.entity.MemberMission;
import org.example.umc10th_m4.domain.mission.entity.Mission;
import org.example.umc10th_m4.domain.mission.repository.MemberMissionRepository;
import org.example.umc10th_m4.domain.mission.repository.MissionRepository;
import org.example.umc10th_m4.domain.member.error.MemberErrorStatus;
import org.example.umc10th_m4.domain.member.repository.MemberRepository;
import org.example.umc10th_m4.domain.region.entity.Region;
import org.example.umc10th_m4.domain.region.error.RegionErrorStatus;
import org.example.umc10th_m4.domain.region.repository.RegionRepository;
import org.example.umc10th_m4.global.status.GeneralException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionServiceImpl implements MissionService {

    private static final int PAGE_SIZE = 10;

    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final RegionRepository regionRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<MissionResponseDto> getMissionsByRegion(String regionName, int page) {
        Region region = regionRepository.findByName(regionName)
                .orElseThrow(() -> new GeneralException(RegionErrorStatus.REGION_NOT_FOUND));

        return missionRepository.findByRegionId(region.getId(), PageRequest.of(page - 1, PAGE_SIZE))
                .stream()
                .map(MissionResponseDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<MissionResponseDto> getMyMissions(long memberId, String status, int page) {
        memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorStatus.MEMBER_NOT_FOUND));

        return memberMissionRepository.findByMemberIdAndStatus(memberId, status, PageRequest.of(page - 1, PAGE_SIZE))
                .stream()
                .map(MissionResponseDto::from)
                .collect(Collectors.toList());
    }

}
