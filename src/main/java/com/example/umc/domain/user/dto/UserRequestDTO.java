package com.example.umc.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserRequestDTO {

    private UserRequestDTO() {
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageRequest {

        @NotNull(message = "유저 ID는 필수입니다.")
        private Long id;
    }
}
