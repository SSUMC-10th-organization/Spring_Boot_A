package org.example.umc10th_m4.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ReviewRequestDto {
    @NotNull(message = "회원 ID는 필수입니다")
    private Long memberId;

    @NotNull(message = "별점은 필수입니다")
    @Min(value = 1, message = "별점은 1 이상이어야 합니다")
    @Max(value = 5, message = "별점은 5 이하이어야 합니다")
    private Integer score;

    @NotBlank(message = "리뷰 내용은 필수입니다")
    private String detail;
}
