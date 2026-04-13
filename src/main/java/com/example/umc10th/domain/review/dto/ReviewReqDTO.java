package com.example.umc10th.domain.review.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class ReviewReqDTO {

    @Getter
    public static class CreateReviewReq {
        private Integer rating;
        private String content;
        private List<MultipartFile> images; // 선택
    }
}