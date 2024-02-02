package com.example.medhub.repository;

import com.example.medhub.entity.AvailabilityEntity;
import com.example.medhub.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface AvailabilityRepository extends JpaRepository<AvailabilityEntity, Long> {
    List<AvailabilityEntity> findByDoctorId_DoctorId(Long doctorId);
}
