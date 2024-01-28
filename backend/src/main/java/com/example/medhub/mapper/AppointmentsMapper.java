package com.example.medhub.mapper;

import com.example.medhub.dto.AppointmentsDto;
import com.example.medhub.entity.AppointmentsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class AppointmentsMapper {
    public static final AppointmentsMapper APPOINTMENTS_MAPPER = Mappers.getMapper(AppointmentsMapper.class);

    // Mapping from Appointment entity to AppointmentDto
    @Mapping(target = "user", source = "userEntity")
    @Mapping(target = "doctor", source = "doctorEntity")
    @Mapping(target = "location", source = "locationEntity")
    public abstract AppointmentsDto toAppointmentDto(AppointmentsEntity appointment);

}
