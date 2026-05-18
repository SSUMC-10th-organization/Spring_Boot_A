package com.example.umc.domain.mission.repository;

import com.example.umc.domain.mission.entity.Mission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    @Query(value = """
            select m
            from Mission m
            join fetch m.store s
            join fetch s.category c
            where s.address like concat('%', :region, '%')
              and (m.deadline is null or m.deadline >= :today)
              and m.deletedAt is null
            """,
            countQuery = """
                    select count(m)
                    from Mission m
                    join m.store s
                    where s.address like concat('%', :region, '%')
                      and (m.deadline is null or m.deadline >= :today)
                      and m.deletedAt is null
                    """)
    Page<Mission> findAvailableMissionsByRegion(
            @Param("region") String region,
            @Param("today") LocalDate today,
            Pageable pageable
    );
}
