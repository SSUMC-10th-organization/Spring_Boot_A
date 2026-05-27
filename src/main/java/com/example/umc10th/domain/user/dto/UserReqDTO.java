package com.example.umc10th.domain.user.dto;

import com.example.umc10th.domain.mission.enums.MissionStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

public class UserReqDTO {

    @Getter
    public static class JoinReq {

        @NotBlank(message = "이름은 필수입니다.")
        private String name;

        @NotBlank(message = "성별은 필수입니다.")
        private String gender;

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이 아닙니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다.")
        private String password;

        @NotBlank(message = "닉네임은 필수입니다.")
        @Size(max = 20, message = "닉네임은 20자 이하여야 합니다.")
        private String nickname;

        @NotBlank(message = "주소는 필수입니다.")
        private String address;

        @NotNull(message = "생년월일은 필수입니다.")
        private LocalDate birth;

        private String phone;
    }

    public record Login(String email, String password) {}

    @Getter
    public static class MissionSuccessReq {

        @NotNull(message = "미션 상태는 필수입니다.")
        private MissionStatus status;
    }
}
