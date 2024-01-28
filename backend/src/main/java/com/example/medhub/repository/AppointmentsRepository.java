package com.example.medhub.repository;

import com.example.medhub.entity.AppointmentsEntity;
import com.example.medhub.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentsRepository extends JpaRepository<AppointmentsEntity, Long> {
    List<AppointmentsEntity> findByUserEntityUserId(Long userId);
}