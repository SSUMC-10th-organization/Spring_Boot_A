package com.example.umc10th.domain.user.service;

import com.example.umc10th.domain.user.dto.UserReqDTO;
import com.example.umc10th.domain.user.dto.UserResDTO;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.user.enums.Gender;
import com.example.umc10th.domain.user.exception.UserException;
import com.example.umc10th.domain.user.exception.code.UserErrorCode;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResDTO.MyPageRes getMyPage(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException(UserErrorCode.USER_NOT_FOUND));

        return UserResDTO.MyPageRes.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .phoneNum(user.getPhoneNum())
                .point(user.getPoint())
                .build();
    }

    @Override
    @Transactional
    public UserResDTO.JoinRes joinUser(UserReqDTO.JoinReq request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserException(UserErrorCode.USER_EMAIL_DUPLICATE);
        }

        User user = User.builder()
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .gender(Gender.valueOf(request.getGender()))
                .birthDate(request.getBirth())
                .address(request.getAddress())
                .phoneNum(request.getPhone())
                .build();

        User saved = userRepository.save(user);

        return UserResDTO.JoinRes.builder()
                .userId(saved.getId())
                .email(saved.getEmail())
                .build();
    }
}
