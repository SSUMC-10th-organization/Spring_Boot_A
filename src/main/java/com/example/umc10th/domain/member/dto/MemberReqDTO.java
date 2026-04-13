package com.example.demo.member.dto;

import java.time.LocalDate;

public class MemberReqDTO {

    public record Signup(
            String email,
            String password,
            String name,
            String phoneNumber,
            LocalDate birthDate,
            String gender
    ) {}
}
