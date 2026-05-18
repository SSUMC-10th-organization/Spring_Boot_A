package org.example.umc10th_m4.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.umc10th_m4.domain.mission.entity.MemberMission;
import org.example.umc10th_m4.domain.mission.entity.Mission;

@Getter
@Builder
public class MissionResponseDto {
    private long missionId;
    private String storeName;
    private String detail;
    private int point;
    private String status;
    private int count;

    public static MissionResponseDto from(Mission mission) {
        return MissionResponseDto.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .detail(mission.getDetail())
                .point(mission.getPoint())
                .build();
    }

    public static MissionResponseDto from(MemberMission memberMission) {
        return MissionResponseDto.builder()
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getStore().getName())
                .detail(memberMission.getMission().getDetail())
                .point(memberMission.getMission().getPoint())
                .status(memberMission.getStatus())
                .count(memberMission.getMissionCount() != null ? memberMission.getMissionCount() : 0)
                .build();
    }
}
