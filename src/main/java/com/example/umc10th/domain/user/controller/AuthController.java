package com.example.umc10th.domain.user.controller;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.exception.code.UserSuccessCode;
import com.example.umc10th.domain.user.service.UserService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증", description = "회원가입/로그인 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // 로그인 페이지
    @GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> loginPage(@RequestParam(required = false) String error,
                                            @RequestParam(required = false) String logout) {
        String message = "";
        if (error != null) message = "<p style='color:red'>이메일 또는 비밀번호가 올바르지 않습니다.</p>";
        if (logout != null) message = "<p style='color:blue'>로그아웃 되었습니다.</p>";

        String html = """
                <!DOCTYPE html>
                <html lang="ko">
                <head>
                    <meta charset="UTF-8">
                    <title>Please sign in</title>
                    <style>
                        body { display:flex; justify-content:center; align-items:center; height:100vh; margin:0; background:#f5f5f5; font-family:sans-serif; }
                        .box { background:white; padding:40px; border-radius:8px; width:360px; box-shadow:0 2px 8px rgba(0,0,0,0.1); }
                        h2 { margin-bottom:24px; }
                        input { width:100%%; padding:12px; margin-bottom:16px; border:1px solid #ccc; border-radius:4px; box-sizing:border-box; font-size:14px; }
                        button { width:100%%; padding:12px; background:#1a73e8; color:white; border:none; border-radius:4px; font-size:16px; cursor:pointer; }
                        button:hover { background:#1558b0; }
                    </style>
                </head>
                <body>
                    <div class="box">
                        <h2>Please sign in</h2>
                        %s
                        <form method="post" action="/auth/login">
                            <input type="email" name="email" placeholder="이메일" required autofocus/>
                            <input type="password" name="password" placeholder="비밀번호" required/>
                            <button type="submit">Sign in</button>
                        </form>
                    </div>
                </body>
                </html>
                """.formatted(message);

        return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
    }

    // 회원가입
    @Operation(summary = "회원가입")
    @PostMapping("/users")
    public ApiResponse<UserResDTO.JoinRes> join(
            @Valid @RequestBody UserReqDTO.JoinReq request
    ) {
        return ApiResponse.onSuccess(UserSuccessCode.USER_JOIN_OK, userService.joinUser(request));
    }

    // JWT 로그인
    @Operation(summary = "로그인", description = "이메일/비밀번호로 로그인 후 JWT 액세스 토큰을 반환합니다.")
    @PostMapping("/login")
    public ApiResponse<UserResDTO.LoginRes> login(
            @RequestBody UserReqDTO.Login request
    ) {
        String token = userService.login(request.email(), request.password());
        return ApiResponse.onSuccess(UserSuccessCode.USER_LOGIN_OK, new UserResDTO.LoginRes(token));
    }
}