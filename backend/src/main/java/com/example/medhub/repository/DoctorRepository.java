package com.example.medhub.repository;

import com.example.medhub.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {
    List<DoctorEntity> findBySpecializationEntity_SpecializationId(Long specializationId);
}
