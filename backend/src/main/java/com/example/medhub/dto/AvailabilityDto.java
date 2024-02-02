package com.example.medhub.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Data
@Getter
@RequiredArgsConstructor
public class AvailabilityDto {
    private Long availabilityId;
    private Long doctorId;
    private Integer dayOfWeek;
    private String startTime;
    private String endTime;
}
