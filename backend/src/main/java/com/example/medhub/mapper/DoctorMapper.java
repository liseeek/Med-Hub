package com.example.medhub.mapper;

import com.example.medhub.dto.SpecializationDto;
import com.example.medhub.dto.request.DoctorCreateRequestDto;
import com.example.medhub.dto.DoctorDto;
import com.example.medhub.entity.DoctorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class DoctorMapper {
    public static final DoctorMapper DOCTOR_MAPPER = Mappers.getMapper(DoctorMapper.class);

    @Mapping(target = "doctorId", ignore = true)
    public abstract DoctorEntity toDoctor(DoctorCreateRequestDto createRequestDto);

    public abstract DoctorDto toDoctorDto(DoctorEntity doctorEntity);

    @Mapping(target = "specialization", source = "specialization")
    public abstract DoctorDto toDoctorDto(DoctorEntity doctorEntity, SpecializationDto specialization);
}
