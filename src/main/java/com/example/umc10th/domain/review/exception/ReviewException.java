package com.example.umc10th.domain.review.exception;

import com.example.umc10th.global.exception.GeneralException;

public class ReviewException extends GeneralException {
    public ReviewException(ReviewErrorCode code) {
        super(code);
    }
}