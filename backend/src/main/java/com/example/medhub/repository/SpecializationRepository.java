package com.example.medhub.repository;

import com.example.medhub.entity.SpecializationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<SpecializationEntity, Long> {
    List<SpecializationEntity> findAllBySpecializationIdIn(List<Long> specializationId);
}
