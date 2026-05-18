package org.example.umc10th_m4.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MemberSignupRequestDto {
    @NotBlank(message = "이메일은 필수입니다")
    @Email(message = "이메일 형식이 올바르지 않습니다")
    private String email;

    @NotBlank(message = "비밀번호는 필수입니다")
    @Size(min = 8, message = "비밀번호는 8자 이상이어야 합니다")
    private String password;

    @NotBlank(message = "이름은 필수입니다")
    private String name;

    private String number;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String detailAddress;
    private List<String> foodPreferences;
}
