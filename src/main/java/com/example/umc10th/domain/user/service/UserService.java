package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.dto.UserResDTO;

public interface UserService {
    UserResDTO.MyPageRes getMyPage(Long userId);
}
