package org.example.umc10th_m4.domain.review.dto;

import lombok.Builder;
import lombok.Getter;
import org.example.umc10th_m4.domain.review.entity.Review;

@Getter
@Builder
public class ReviewResponseDto {
    private long reviewId;
    private String storeName;
    private String memberName;
    private int score;
    private String detail;
    private String createdAt;

    public static ReviewResponseDto from(Review review) {
        return ReviewResponseDto.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .memberName(review.getMember().getName())
                .score(review.getScore())
                .detail(review.getDetail())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toString() : null)
                .build();
    }
}
