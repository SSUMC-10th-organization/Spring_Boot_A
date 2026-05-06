package org.example.umc10th_m4.domain.member.repository;

import org.example.umc10th_m4.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
