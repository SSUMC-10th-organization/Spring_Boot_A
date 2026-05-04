package org.example.umc10th_m4.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.member.dto.MemberResponseDto;
import org.example.umc10th_m4.domain.member.entity.Member;
import org.example.umc10th_m4.domain.member.error.MemberErrorStatus;
import org.example.umc10th_m4.domain.member.repository.MemberRepository;
import org.example.umc10th_m4.global.status.GeneralException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDto getMember(long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new GeneralException(MemberErrorStatus.MEMBER_NOT_FOUND));

        return MemberResponseDto.builder()
                .userId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .point(member.getPoint() != null ? member.getPoint() : 0)
                .build();
    }
}
