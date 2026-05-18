package org.example.umc10th_m4.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String password;
    private String name;
    private Integer point;
    private String status;
    private String number;
    private Integer likes;
    private String gender;
    private LocalDate birthDate;
    private String address;
    private String detailAddress;

    @ElementCollection
    @CollectionTable(name = "member_food_preference", joinColumns = @JoinColumn(name = "member_id"))
    @Column(name = "food_type")
    @Builder.Default
    private List<String> foodPreferences = new ArrayList<>();
}
