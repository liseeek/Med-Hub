package com.example.medhub.repository;

import com.example.medhub.entity.DoctorSpecialization;
import com.example.medhub.entity.keys.DoctorSpecializationId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, DoctorSpecializationId> {
    List<DoctorSpecialization> findAllByDoctorSpecializationIdDoctorIdIn(List<Long> doctorIds);

}
