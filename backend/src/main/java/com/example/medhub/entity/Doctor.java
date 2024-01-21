package com.example.medhub.entity;

import com.example.medhub.dto.create.DoctorCreateRequestDto;
import com.example.medhub.mapper.DoctorMapper;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "doctors")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private long doctorId;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;

    public static Doctor from(DoctorCreateRequestDto newDoctor) {
        return DoctorMapper.DOCTOR_MAPPER.toDoctor(newDoctor);
    }
}
