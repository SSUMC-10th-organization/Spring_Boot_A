package com.example.umc10th.global.entity;

import com.example.umc10th.domain.member.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public class OAuthMember implements OAuth2User {

    private final Member member;
    private final Map<String, Object> attributes;

    @Override
    public String getName() {
        return member.getSocialUid();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }
}
