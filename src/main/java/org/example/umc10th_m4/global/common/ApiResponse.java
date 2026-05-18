package org.example.umc10th_m4.global.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.umc10th_m4.global.status.BaseStatus;
import org.example.umc10th_m4.global.status.SuccessStatus;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({"isSuccess", "code", "message", "result"})
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("result")
    private final T result;

    // 기본 200 OK 응답일 때 사용
    public static <T> ApiResponse<T> onSuccess(T result) {
        return new ApiResponse<>(true, SuccessStatus.OK.getCode(), SuccessStatus.OK.getMessage(), result);
    }

    // 201 Created 등 다른 상태 코드를 지정해야 할 때 사용 (기존 of 메서드 이름 변경)
    public static <T> ApiResponse<T> onSuccess(BaseStatus status, T result) {
        return new ApiResponse<>(true, status.getCode(), status.getMessage(), result);
    }

    public static <T> ApiResponse<T> onFailure(String code, String message, T data) {
        return new ApiResponse<>(false, code, message, data);
    }

    public static <T> ApiResponse<T> onFailure(BaseStatus status, T data) {
        return new ApiResponse<>(false, status.getCode(), status.getMessage(), data);
    }
}