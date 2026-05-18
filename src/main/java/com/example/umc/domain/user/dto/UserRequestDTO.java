package com.example.umc.domain.user.dto;

import com.example.umc.domain.user.entity.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

public class UserRequestDTO {

    private UserRequestDTO() {
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyPageRequest {

        @NotNull(message = "유저 ID는 필수입니다.")
        @Positive(message = "유저 ID는 양수여야 합니다.")
        private Long id;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyInProgressMissionRequest {

        @NotNull(message = "유저 ID는 필수입니다.")
        @Positive(message = "유저 ID는 양수여야 합니다.")
        private Long userId;

        @PositiveOrZero(message = "페이지 번호는 0 이상이어야 합니다.")
        private Integer pageNumber = 0;

        @Positive(message = "페이지 크기는 양수여야 합니다.")
        private Integer pageSize = 10;

        public Integer getPageNumber() {
            return pageNumber == null ? 0 : pageNumber;
        }

        public Integer getPageSize() {
            return pageSize == null ? 10 : pageSize;
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpRequest {

        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        private String email;

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, max = 30, message = "비밀번호는 8자 이상 30자 이하여야 합니다.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).+$",
                message = "비밀번호는 영문과 숫자를 포함해야 합니다."
        )
        private String password;

        @NotBlank(message = "이름은 필수입니다.")
        @Size(max = 255, message = "이름은 255자 이하여야 합니다.")
        private String name;

        @Past(message = "생년월일은 과거 날짜여야 합니다.")
        private LocalDate birthDate;

        private Gender gender;

        @Size(max = 255, message = "주소는 255자 이하여야 합니다.")
        private String address;
    }
}
