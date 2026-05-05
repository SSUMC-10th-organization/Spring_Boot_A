package org.example.umc10th_m4.domain.store.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

public class StoreRequestDto {
    @Getter
    @NoArgsConstructor
    public static class UpdateStoreDto {
        private String name;
        private String location;
    }
}