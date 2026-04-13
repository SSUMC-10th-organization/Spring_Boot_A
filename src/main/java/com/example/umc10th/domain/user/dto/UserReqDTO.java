package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import lombok.Getter;
import java.time.LocalDate;

public class UserReqDTO {

    @Getter
    public static class JoinReq {
        private String name;
        private String gender;
        private String email;
        private String password;
        private String nickname;
        private String address;
        private LocalDate birth;
        private String phone; // 선택
    }

    @Getter
    public static class MissionSuccessReq {
        private MissionStatus status;
    }
}