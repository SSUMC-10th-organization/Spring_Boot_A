package com.example.umc.domain.user.service;

import com.example.umc.domain.user.code.UserErrorCode;
import com.example.umc.domain.user.converter.UserConverter;
import com.example.umc.domain.user.dto.UserRequestDTO;
import com.example.umc.domain.user.dto.UserResponseDTO;
import com.example.umc.domain.user.entity.User;
import com.example.umc.domain.user.exception.UserException;
import com.example.umc.domain.user.repository.UserRepository;
import com.example.umc.domain.userMission.entity.UserMission;
import com.example.umc.domain.userMission.entity.UserMissionStatus;
import com.example.umc.domain.userMission.repository.UserMissionRepository;
import com.example.umc.global.security.CustomUserDetails;
import com.example.umc.global.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final UserMissionRepository userMissionRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponseDTO.SignUpResponse signUp(UserRequestDTO.SignUpRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserException(UserErrorCode.EMAIL_ALREADY_EXISTS);
        }

        User user = UserConverter.toUser(request, passwordEncoder.encode(request.getPassword()));
        return UserConverter.toSignUpResponse(userRepository.save(user));
    }

    public UserResponseDTO.LoginResponse login(UserRequestDTO.LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserException(UserErrorCode.INVALID_EMAIL_OR_PASSWORD));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new UserException(UserErrorCode.INVALID_EMAIL_OR_PASSWORD);
        }

        String accessToken = jwtUtil.generateAccessToken(new CustomUserDetails(user));
        return UserConverter.toLoginResponse(accessToken);
    }

    public UserResponseDTO.MyPageResponse getMyPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));
        return UserConverter.toMyPageResponse(user);
    }

    public UserResponseDTO.UserMissionPreviewListResponse getMyMissions(
            Long userId,
            UserMissionStatus status,
            Integer page
    ) {
        if (!userRepository.existsById(userId)) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        Page<UserMission> userMissions = userMissionRepository.findUserMissionsByStatus(
                userId,
                status,
                PageRequest.of(page, 10)
        );
        return UserConverter.toUserMissionPreviewListResponse(userMissions);
    }

    public UserResponseDTO.UserMissionPreviewListResponse getMyInProgressMissions(
            UserRequestDTO.MyInProgressMissionRequest request
    ) {
        if (!userRepository.existsById(request.getUserId())) {
            throw new UserException(UserErrorCode.USER_NOT_FOUND);
        }

        Page<UserMission> userMissions = userMissionRepository.findUserMissionsByStatus(
                request.getUserId(),
                UserMissionStatus.IN_PROGRESS,
                PageRequest.of(request.getPageNumber(), request.getPageSize())
        );
        return UserConverter.toUserMissionPreviewListResponse(userMissions);
    }
}
