package com.example.medhub.service;

import com.example.medhub.dto.LocationDto;
import com.example.medhub.dto.SpecializationDto;
import com.example.medhub.dto.request.DoctorCreateRequestDto;
import com.example.medhub.dto.DoctorDto;
import com.example.medhub.entity.DoctorEntity;
import com.example.medhub.entity.LocationEntity;
import com.example.medhub.entity.SpecializationEntity;
import com.example.medhub.repository.DoctorRepository;
import com.example.medhub.repository.LocationRepository;
import com.example.medhub.repository.SpecializationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final LocationRepository locationRepository;
    private final SpecializationRepository specializationRepository;

    @Transactional
    public DoctorDto saveDoctor(DoctorCreateRequestDto newDoctorDto) {
        LocationEntity locationEntity = new LocationEntity(null, newDoctorDto.getLocationName(), newDoctorDto.getAddress(), newDoctorDto.getCity(), newDoctorDto.getCountry());
        locationEntity = locationRepository.save(locationEntity);

        SpecializationEntity specializationEntity = specializationRepository.findById(newDoctorDto.getSpecializationId())
                .orElseThrow(() -> new RuntimeException("Specialization not found"));

        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setName(newDoctorDto.getName());
        doctorEntity.setSurname(newDoctorDto.getSurname());
        doctorEntity.setLocationEntity(locationEntity);
        doctorEntity.setSpecializationEntity(specializationEntity);
        DoctorEntity savedDoctorEntity = doctorRepository.save(doctorEntity);

        SpecializationDto specializationDto = new SpecializationDto(specializationEntity.getSpecializationId(), specializationEntity.getSpecializationName());

        return DoctorDto.from(savedDoctorEntity, specializationDto);
    }

    public List<DoctorDto> getAllDoctors() {
        List<DoctorEntity> allDoctorEntities = doctorRepository.findAll();

        return allDoctorEntities.stream()
                .map(doctor -> {
                    SpecializationDto specializationDto = null;
                    if (doctor.getSpecializationEntity() != null) {
                        SpecializationEntity specializationEntity = doctor.getSpecializationEntity();
                        specializationDto = new SpecializationDto(specializationEntity.getSpecializationId(), specializationEntity.getSpecializationName());
                    }
                    return DoctorDto.from(doctor, specializationDto);
                })
                .collect(Collectors.toList());
    }

    public DoctorDto getDoctor(Long id) {
        DoctorEntity doctorEntity = doctorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Doctor with id = %s not found".formatted(id)));

        SpecializationDto specializationDto = null;
        if (doctorEntity.getSpecializationEntity() != null) {
            SpecializationEntity specializationEntity = doctorEntity.getSpecializationEntity();
            specializationDto = new SpecializationDto(specializationEntity.getSpecializationId(), specializationEntity.getSpecializationName());
        }

        LocationEntity locationEntity = doctorEntity.getLocationEntity();
        LocationDto locationDto = locationEntity != null ?
                new LocationDto(locationEntity.getLocationId(), locationEntity.getLocationName(), locationEntity.getAddress(), locationEntity.getCity(), locationEntity.getCountry()) : null;

        return new DoctorDto(doctorEntity.getDoctorId(), doctorEntity.getName(), doctorEntity.getSurname(), locationDto, specializationDto);
    }
}