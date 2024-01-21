package com.example.medhub.service;

import com.example.medhub.dto.create.DoctorCreateRequestDto;
import com.example.medhub.dto.DoctorDto;
import com.example.medhub.entity.Doctor;
import com.example.medhub.entity.DoctorSpecialization;
import com.example.medhub.entity.Specialization;
import com.example.medhub.entity.Location;
import com.example.medhub.entity.keys.DoctorSpecializationId;
import com.example.medhub.repository.DoctorRepository;
import com.example.medhub.repository.DoctorSpecializationRepository;
import com.example.medhub.repository.LocationRepository;
import com.example.medhub.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final LocationRepository locationRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;

    @Transactional
    public DoctorDto saveDoctor(DoctorCreateRequestDto newDoctorDto) {
        // Create and save the location
        Location location = new Location(null, newDoctorDto.getLocationName(),
                newDoctorDto.getAddress(), newDoctorDto.getCity(),
                newDoctorDto.getCountry());
        location = locationRepository.save(location);

        // Create and save the doctor with the new location
        Doctor doctor = Doctor.from(newDoctorDto);
        doctor.setLocation(location); // Associate the doctor with the location
        Doctor savedDoctor = doctorRepository.save(doctor);

        // Handle a single specialization
        Specialization specialization = specializationRepository.findById(newDoctorDto.getSpecializationIds())
                .orElseThrow(() -> new NotFoundException("Specialization not found: " + newDoctorDto.getSpecializationIds()));

        DoctorSpecializationId doctorSpecializationId = new DoctorSpecializationId(savedDoctor.getDoctorId(), specialization.getSpecializationId());
        DoctorSpecialization doctorSpecialization = new DoctorSpecialization(doctorSpecializationId);
        doctorSpecializationRepository.save(doctorSpecialization);

        // Convert Doctor to DoctorDto
        return DoctorDto.from(savedDoctor);
    }

    public List<DoctorDto> getAllDoctors() {
        List<Doctor> allDoctors = doctorRepository.findAll();
        List<Long> doctorIds = allDoctors.stream()
                .map(Doctor::getDoctorId)
                .toList();
        List<DoctorSpecialization> allByDoctorSpecializationIdDoctorIdIn = doctorSpecializationRepository.findAllByDoctorSpecializationIdDoctorIdIn(doctorIds);
        List<Long> specializationIds = allByDoctorSpecializationIdDoctorIdIn.stream()
                        .map(id -> id.getDoctorSpecializationId().getSpecializationId())
                                .toList();
        List<Specialization> doctorSpecializationIds = specializationRepository.findAllBySpecializationIdIn(specializationIds);
        return allDoctors.stream()
                .map(DoctorDto::from)
                .toList();
    }

    public DoctorDto getDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor with id = %s not found".formatted(id)));
        doctorSpecializationRepository.findById
        return DoctorDto.from(doctor);
    }
}