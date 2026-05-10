package org.example.umc10th_m4.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.mission.dto.MissionResponseDto;
import org.example.umc10th_m4.domain.mission.dto.MyMissionRequestDto;
import org.example.umc10th_m4.domain.mission.entity.MemberMission;
import org.example.umc10th_m4.domain.mission.entity.Mission;
import org.example.umc10th_m4.domain.mission.repository.MemberMissionRepository;
import org.example.umc10th_m4.domain.mission.repository.MissionRepository;
import org.example.umc10th_m4.domain.member.error.MemberErrorStatus;
import org.example.umc10th_m4.domain.member.repository.MemberRepository;
import org.example.umc10th_m4.domain.region.entity.Region;
import org.example.umc10th_m4.domain.region.error.RegionErrorStatus;
import org.example.umc10th_m4.domain.region.repository.RegionRepository;
import org.example.umc10th_m4.global.common.PageResponse;
import org.example.umc10th_m4.global.status.GeneralException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
                .map(this::missionToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PageResponse<MissionResponseDto> getMyOngoingMissions(MyMissionRequestDto request, int page, int pageSize) {
        memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new GeneralException(MemberErrorStatus.MEMBER_NOT_FOUND));

        Page<MemberMission> result = memberMissionRepository.findByMemberIdAndStatus(
                request.getMemberId(),
                "ONGOING",
                PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.DESC, "id")));

        Page<MissionResponseDto> dtoPage = result.map(this::memberMissionToDto);
        return PageResponse.from(dtoPage);
    }

    private MissionResponseDto missionToDto(Mission mission) {
        return MissionResponseDto.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .detail(mission.getDetail())
                .point(mission.getPoint())
                .build();
    }

    private MissionResponseDto memberMissionToDto(MemberMission mm) {
        return MissionResponseDto.builder()
                .missionId(mm.getMission().getId())
                .storeName(mm.getStore().getName())
                .detail(mm.getMission().getDetail())
                .point(mm.getMission().getPoint())
                .status(mm.getStatus())
                .count(mm.getMissionCount() != null ? mm.getMissionCount() : 0)
                .build();
    }
}
