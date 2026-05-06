package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.dto.MissionResDTO;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.entity.UserMission;
import com.example.umc10th.domain.mission.enums.MissionStatus;
import com.example.umc10th.domain.mission.repository.MissionRepository;
import com.example.umc10th.domain.mission.repository.UserMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;
    private final MemberRepository memberRepository;

    // 내 미션 목록 조회 (진행중, 진행완료)
    public MissionResDTO.MissionListResult getMissionList(Long memberId, MissionStatus status, int page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        Page<UserMission> userMissions = userMissionRepository
                .findByMemberAndStatus(member, status, PageRequest.of(page, 10));

        List<MissionResDTO.MissionDetail> missionDetails = userMissions.stream()
                .map(um -> new MissionResDTO.MissionDetail(
                        um.getMission().getId(),
                        um.getMission().getStore().getName(),
                        um.getMission().getMissionSpec(),
                        um.getMission().getReward(),
                        um.getStatus().name()
                )).toList();

        return new MissionResDTO.MissionListResult(missionDetails, (int) userMissions.getTotalElements());
    }

    // 미션 완료 처리
    @Transactional
    public MissionResDTO.MissionCompleteResult completeMission(Long missionId) {
        UserMission userMission = userMissionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 미션입니다."));

        return new MissionResDTO.MissionCompleteResult(
                userMission.getId(),
                MissionStatus.COMPLETE.name(),
                userMission.getUpdatedAt()
        );
    }

    // 홈화면 - 특정 지역 미션 목록 조회
    public MissionResDTO.MissionListResult getHomeMissions(Long locationId, int page) {
        Page<Mission> missions = missionRepository
                .findMissionsByLocation(locationId, PageRequest.of(page, 10));

        List<MissionResDTO.MissionDetail> missionDetails = missions.stream()
                .map(m -> new MissionResDTO.MissionDetail(
                        m.getId(),
                        m.getStore().getName(),
                        m.getMissionSpec(),
                        m.getReward(),
                        null
                )).toList();

        return new MissionResDTO.MissionListResult(missionDetails, (int) missions.getTotalElements());
    }
}