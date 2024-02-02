package com.example.medhub.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "appointments")
public class AppointmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctorEntity;

    @Column(name = "appointment_date")
    private LocalDate date;

    @Column(name = "appointment_time")
    private LocalTime time;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationEntity locationEntity;

}

