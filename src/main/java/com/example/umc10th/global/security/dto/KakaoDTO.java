package com.example.umc10th.global.security.dto;

public record KakaoDTO(String socialUid, String email, String name) implements OAuthDTO {}
