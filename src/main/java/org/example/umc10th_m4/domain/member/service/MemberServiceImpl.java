package org.example.umc10th_m4.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.member.dto.MemberResponseDto;
import org.example.umc10th_m4.domain.member.dto.MemberSignupRequestDto;
import org.example.umc10th_m4.domain.member.entity.Member;
import org.example.umc10th_m4.domain.member.error.MemberErrorStatus;
import org.example.umc10th_m4.domain.member.repository.MemberRepository;
import org.example.umc10th_m4.global.status.GeneralException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public MemberResponseDto getMember(long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorStatus.MEMBER_NOT_FOUND));

        return MemberResponseDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .point(member.getPoint() != null ? member.getPoint() : 0)
                .build();
    }

    @Override
    public MemberResponseDto getMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new GeneralException(MemberErrorStatus.MEMBER_NOT_FOUND));
        return MemberResponseDto.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .point(member.getPoint() != null ? member.getPoint() : 0)
                .build();
    }

    @Override
    @Transactional
    public MemberResponseDto signUp(MemberSignupRequestDto request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new GeneralException(MemberErrorStatus.EMAIL_ALREADY_EXIST);
        }

        Member member = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .number(request.getNumber())
                .gender(request.getGender())
                .birthDate(request.getBirthDate())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .foodPreferences(request.getFoodPreferences() != null ? request.getFoodPreferences() : new ArrayList<>())
                .point(0)
                .status("ACTIVE")
                .likes(0)
                .build();

        Member saved = memberRepository.save(member);

        return MemberResponseDto.builder()
                .memberId(saved.getId())
                .name(saved.getName())
                .email(saved.getEmail())
                .point(0)
                .build();
    }
}
