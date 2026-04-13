package org.example.umc10th_m4.domain.store.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StoreResponseDto {
    private long storeId;
    private String name;
    private String location;
    private String updatedAt;
}