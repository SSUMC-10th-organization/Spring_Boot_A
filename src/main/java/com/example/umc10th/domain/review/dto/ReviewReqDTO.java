package com.example.umc10th.domain.review.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewReqDTO {

    @Getter
    @Schema(description = "리뷰 작성 요청")
    public static class CreateReviewReq {

        @NotNull
        @Min(1) @Max(5)
        @Schema(description = "별점 (1~5)", example = "4")
        private Integer rating;

        @NotBlank
        @Schema(description = "리뷰 내용", example = "맛있었어요!")
        private String content;

        @NotNull
        @Schema(description = "사용자 미션 ID", example = "1")
        private Long userMissionId;
    }
}
