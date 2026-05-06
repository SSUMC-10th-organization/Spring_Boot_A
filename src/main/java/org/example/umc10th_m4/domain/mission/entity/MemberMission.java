package org.example.umc10th_m4.domain.mission.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.umc10th_m4.domain.member.entity.Member;
import org.example.umc10th_m4.domain.region.entity.Region;
import org.example.umc10th_m4.domain.store.entity.Store;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberMission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer missionCount;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mission_id")
    private Mission mission;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
}
