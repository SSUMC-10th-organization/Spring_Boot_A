package org.example.umc10th_m4.domain.review.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDto {
    private long memberId;
    private int score;
    private String detail;
}
