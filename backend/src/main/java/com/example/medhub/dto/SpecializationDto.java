package com.example.medhub.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SpecializationDto {
    private final Long specializationId;
    private final String specializationName;

    // You can add additional fields if needed
}

