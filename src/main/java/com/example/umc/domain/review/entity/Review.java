package com.example.umc.domain.review.entity;

import com.example.umc.domain.common.BaseEntity;
import com.example.umc.domain.userMission.entity.UserMission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "reviews")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_mission_id", nullable = false, unique = true)
    private UserMission userMission;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false, length = 255)
    private String content;

    @Builder
    private Review(UserMission userMission, Integer rating, String content) {
        this.userMission = userMission;
        this.rating = rating;
        this.content = content;
    }
}
