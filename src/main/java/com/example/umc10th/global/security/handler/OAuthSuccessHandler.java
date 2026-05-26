package com.example.umc10th.global.security.handler;

import com.example.umc10th.global.entity.AuthMember;
import com.example.umc10th.global.entity.OAuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@RequiredArgsConstructor
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuthMember oAuthMember = (OAuthMember) authentication.getPrincipal();
        String accessToken = jwtUtil.createAccessToken(new AuthMember(oAuthMember.getMember()));

        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"accessToken\": \"" + accessToken + "\"}");
    }
}
