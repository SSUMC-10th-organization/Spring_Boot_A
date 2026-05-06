package com.example.umc.domain.mission.entity;

import com.example.umc.domain.common.BaseEntity;
import com.example.umc.domain.store.entity.Store;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "missions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "reward_points", nullable = false)
    private Integer rewardPoints;

    private LocalDate deadline;

    @Builder
    private Mission(Store store, String title, Integer rewardPoints, LocalDate deadline) {
        this.store = store;
        this.title = title;
        this.rewardPoints = rewardPoints == null ? 0 : rewardPoints;
        this.deadline = deadline;
    }
}
