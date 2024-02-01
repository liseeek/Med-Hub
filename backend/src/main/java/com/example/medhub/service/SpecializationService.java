package com.example.medhub.service;

import com.example.medhub.dto.SpecializationDto;
import com.example.medhub.repository.SpecializationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.medhub.entity.SpecializationEntity;
import com.example.medhub.mapper.SpecializationMapper;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecializationService {

    @Autowired
    private SpecializationRepository specializationRepository;

    public List<SpecializationDto> getAllSpecializations() {
        return specializationRepository.findAll().stream()
                .map(SpecializationMapper.SPECIALIZATION_MAPPER::entityToDto)
                .collect(Collectors.toList());
    }
}
