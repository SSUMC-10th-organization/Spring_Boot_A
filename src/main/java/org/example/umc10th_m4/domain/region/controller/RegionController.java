package org.example.umc10th_m4.domain.region.controller;

import lombok.RequiredArgsConstructor;
import org.example.umc10th_m4.domain.region.dto.RegionResponseDto;
import org.example.umc10th_m4.global.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/regions")
public class RegionController {

    @GetMapping
    public ApiResponse<List<RegionResponseDto>> getRegions() {
        return ApiResponse.onSuccess(null);
    }
}