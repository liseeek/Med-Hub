package com.example.medhub.service;

import com.example.medhub.dto.AvailabilityDto;
import com.example.medhub.entity.AvailabilityEntity;
import com.example.medhub.repository.AvailabilityRepository;
import com.example.medhub.mapper.AvailabilityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    @Autowired
    private AvailabilityRepository availabilityRepository;

    public List<AvailabilityDto> getAvailabilityForDoctor(Long doctorId) {
        List<AvailabilityEntity> entities = availabilityRepository.findByDoctorId_DoctorId(doctorId);
        return entities.stream()
                .map(AvailabilityMapper.AVAILABILITY_MAPPER::entityToDto)
                .collect(Collectors.toList());
    }
}
