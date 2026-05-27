package org.example.umc10th_m4.global.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.example.umc10th_m4.global.security.CustomUserDetailsService;
import org.example.umc10th_m4.global.security.JwtUtil;
import org.example.umc10th_m4.global.status.ErrorStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    // Fix 3: 매 요청마다 생성하지 않고 static으로 재사용
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization");

            if (token == null || !token.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            token = token.replace("Bearer ", "");

            // Fix 4: isValid + getEmail 두 번 파싱 → getEmail 한 번으로 통합
            // getEmail이 null 반환하면 유효하지 않은 토큰
            String email = jwtUtil.getEmail(token);
            if (email != null) {
                UserDetails user = customUserDetailsService.loadUserByUsername(email);
                Authentication auth = new UsernamePasswordAuthenticationToken(
                        user,
                        null,
                        user.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

            filterChain.doFilter(request, response);

        // Fix 2: JWT/인증 관련 예외만 401 처리
        } catch (JwtException | UsernameNotFoundException e) {
            sendErrorResponse(response);

        // Fix 2: 그 외 예외(DB 장애, 직렬화 실패 등)는 500으로 전파
        } catch (Exception e) {
            throw new ServletException("JWT 필터 내부 오류", e);
        }
    }

    private void sendErrorResponse(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(ErrorStatus.UNAUTHORIZED.getHttpStatus().value());
        objectMapper.writeValue(
                response.getOutputStream(),
                ApiResponse.onFailure(ErrorStatus.UNAUTHORIZED, null)
        );
    }
}
