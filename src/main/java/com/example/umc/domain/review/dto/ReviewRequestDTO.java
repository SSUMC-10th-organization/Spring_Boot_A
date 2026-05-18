package com.example.umc.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
        @Positive(message = "유저 미션 ID는 양수여야 합니다.")
        private Long userMissionId;

        @NotNull(message = "평점은 필수입니다.")
        @Min(value = 1, message = "평점은 1점 이상이어야 합니다.")
        @Max(value = 5, message = "평점은 5점 이하여야 합니다.")
        private Integer rating;

        @NotBlank(message = "리뷰 내용은 필수입니다.")
        @Size(max = 255, message = "리뷰 내용은 255자 이하여야 합니다.")
        private String content;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyReviewCursorRequest {

        @NotNull(message = "유저 ID는 필수입니다.")
        @Positive(message = "유저 ID는 양수여야 합니다.")
        private Long userId;

        @Min(value = 1, message = "조회 개수는 1개 이상이어야 합니다.")
        @Max(value = 50, message = "조회 개수는 50개 이하여야 합니다.")
        private Integer size = 10;

        @Pattern(regexp = "ID|RATING", message = "정렬 기준은 ID 또는 RATING만 가능합니다.")
        private String sortBy = "ID";

        private String cursor;

        public Integer getSize() {
            return size == null ? 10 : size;
        }

        public String getSortBy() {
            return sortBy == null ? "ID" : sortBy;
        }
    }
}
