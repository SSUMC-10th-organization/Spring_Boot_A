package com.example.umc.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
        @Positive(message = "유저 ID는 양수여야 합니다.")
        private Long id;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyInProgressMissionRequest {

        @NotNull(message = "유저 ID는 필수입니다.")
        @Positive(message = "유저 ID는 양수여야 합니다.")
        private Long userId;

        @PositiveOrZero(message = "페이지 번호는 0 이상이어야 합니다.")
        private Integer pageNumber = 0;

        @Positive(message = "페이지 크기는 양수여야 합니다.")
        private Integer pageSize = 10;

        public Integer getPageNumber() {
            return pageNumber == null ? 0 : pageNumber;
        }

        public Integer getPageSize() {
            return pageSize == null ? 10 : pageSize;
        }
    }
}
