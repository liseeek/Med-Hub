package com.example.medhub.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import com.example.medhub.entity.AppointmentsEntity;
import com.example.medhub.mapper.AppointmentsMapper;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AppointmentsDto {
    private final Long appointmentId;
    private final UserDto user;        // Assuming UserDto is already defined
    private final DoctorDto doctor;    // DoctorDto as per your schema
    private final LocalDate date;
    private final LocalTime time;
    private final LocationDto location;// Assuming LocationDto is already defined

    public static AppointmentsDto from(AppointmentsEntity appointment) {
        return AppointmentsMapper.APPOINTMENTS_MAPPER.toAppointmentDto(appointment);
    }
}
