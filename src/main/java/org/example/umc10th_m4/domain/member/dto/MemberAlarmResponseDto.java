package org.example.umc10th_m4.domain.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberAlarmResponseDto {
    private long alarmId;
    private String content;
    private boolean isRead;
    private String createdAt;
}
