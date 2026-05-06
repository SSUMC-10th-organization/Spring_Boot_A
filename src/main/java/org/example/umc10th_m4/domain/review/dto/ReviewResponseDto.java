package org.example.umc10th_m4.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReviewResponseDto {
    private long reviewId;
    private String storeName;
    private String memberName;
    private int score;
    private String detail;
    private String createdAt;
}
