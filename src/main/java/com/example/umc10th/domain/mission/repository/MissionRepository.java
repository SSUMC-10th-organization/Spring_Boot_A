package com.example.umc10th.domain.mission.repository;

import com.example.umc10th.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MissionRepository extends JpaRepository<Member, Long> {

}