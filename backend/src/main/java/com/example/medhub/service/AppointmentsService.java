package com.example.medhub.service;
import com.example.medhub.dto.AppointmentsDto;
import com.example.medhub.dto.request.AppointmentsCreateRequestDto;
import com.example.medhub.entity.AppointmentsEntity;
import com.example.medhub.entity.DoctorEntity;
import com.example.medhub.entity.LocationEntity;
import com.example.medhub.entity.UserEntity;
import com.example.medhub.mapper.AppointmentsMapper;
import com.example.medhub.repository.AppointmentsRepository;
import com.example.medhub.repository.DoctorRepository;
import com.example.medhub.repository.UserRepository;
import com.example.medhub.repository.LocationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentsService {
    private final AppointmentsRepository appointmentsRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    @Transactional
    public AppointmentsDto createAppointment(AppointmentsCreateRequestDto requestDto) {
        UserEntity userEntity = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + requestDto.getUserId()));
        DoctorEntity doctorEntity = doctorRepository.findById(requestDto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with ID: " + requestDto.getDoctorId()));
        LocationEntity locationEntity = locationRepository.findById(requestDto.getLocationId())
                .orElseThrow(() -> new RuntimeException("Location not found with ID: " + requestDto.getLocationId()));

        AppointmentsEntity appointment = new AppointmentsEntity();
        appointment.setUserEntity(userEntity);
        appointment.setDoctorEntity(doctorEntity);
        appointment.setDate(requestDto.getDate());
        appointment.setLocationEntity(locationEntity);
        appointment.setTime(requestDto.getTime());

        AppointmentsEntity savedAppointment = appointmentsRepository.save(appointment);
        return AppointmentsMapper.APPOINTMENTS_MAPPER.toAppointmentDto(savedAppointment);
    }
    public List<AppointmentsDto> getAppointmentsByUserId(Long userId) {
        List<AppointmentsEntity> appointments = appointmentsRepository.findByUserEntityUserId(userId);
        return appointments.stream()
                .map(AppointmentsMapper.APPOINTMENTS_MAPPER::toAppointmentDto)
                .collect(Collectors.toList());
    }

    // Other service methods like getAppointment, updateAppointment, deleteAppointment, etc.
}

