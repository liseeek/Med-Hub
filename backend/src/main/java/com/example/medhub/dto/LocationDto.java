package com.example.medhub.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LocationDto {
    private final Long locationId;
    private final String locationName;
    private final String address;
    private final String city;
    private final String country;
}
