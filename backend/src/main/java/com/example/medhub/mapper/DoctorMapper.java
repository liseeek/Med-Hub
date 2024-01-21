package com.example.medhub.mapper;

import com.example.medhub.dto.create.DoctorCreateRequestDto;
import com.example.medhub.dto.DoctorDto;
import com.example.medhub.entity.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class DoctorMapper {
    public static final DoctorMapper DOCTOR_MAPPER = Mappers.getMapper(DoctorMapper.class);

    @Mapping(target = "doctorId", ignore = true)
    public abstract Doctor toDoctor(DoctorCreateRequestDto createRequestDto);

    public abstract DoctorDto toDoctorDto(Doctor savedDoctor);
}
