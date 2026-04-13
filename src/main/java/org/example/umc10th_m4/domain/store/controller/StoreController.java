package org.example.umc10th_m4.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.store.dto.StoreRequestDto;
import org.example.umc10th_m4.domain.store.dto.StoreResponseDto;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    @GetMapping("/{store_id}")
    public ApiResponse<StoreResponseDto> getStore(@PathVariable(name = "store_id") long storeId) {
        return ApiResponse.onSuccess(null);
    }

    @PatchMapping("/{store_id}")
    public ApiResponse<StoreResponseDto> updateStore(
            @PathVariable(name = "store_id") long storeId,
            @RequestBody StoreRequestDto request) {
        return ApiResponse.onSuccess(null);
    }
}