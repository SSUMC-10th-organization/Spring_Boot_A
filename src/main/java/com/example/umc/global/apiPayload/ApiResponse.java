package com.example.umc.global.apiPayload;

import com.example.umc.global.apiPayload.code.BaseErrorCode;
import com.example.umc.global.apiPayload.code.BaseSuccessCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;
    private final String code;
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T result;

    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, "COMMON200", "성공입니다.", result);
    }

    public static <T> ApiResponse<T> of(BaseSuccessCode code, T result) {
        return new ApiResponse<>(
                code.getReason().isSuccess(),
                code.getReason().code(),
                code.getReason().message(),
                result
        );
    }

    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(
                code.getReason().isSuccess(),
                code.getReason().code(),
                code.getReason().message(),
                result
        );
    }
}
