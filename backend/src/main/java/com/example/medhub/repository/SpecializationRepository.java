package com.example.medhub.repository;

import com.example.medhub.entity.DoctorSpecialization;
import com.example.medhub.entity.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
    List<Specialization> findAllBySpecializationIdIn(List<Long> specializationId);
}
