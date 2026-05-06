package com.example.umc.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDTO {

    private ReviewRequestDTO() {
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateReviewRequest {

        @NotNull(message = "유저 미션 ID는 필수입니다.")
        private Long userMissionId;

        @NotNull(message = "평점은 필수입니다.")
        @Min(value = 1, message = "평점은 1점 이상이어야 합니다.")
        @Max(value = 5, message = "평점은 5점 이하여야 합니다.")
        private Integer rating;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        private String content;
    }
}
