package com.example.medhub.dto;

import com.example.medhub.entity.DoctorEntity;
import com.example.medhub.mapper.DoctorMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class DoctorDto {
    private final Long doctorId;
    private final String name;
    private final String surname;
    private final LocationDto location;
    private final SpecializationDto specialization;

    public static DoctorDto from(DoctorEntity doctorEntity, SpecializationDto specialization) {
        return DoctorMapper.DOCTOR_MAPPER.toDoctorDto(doctorEntity, specialization);
    }
}