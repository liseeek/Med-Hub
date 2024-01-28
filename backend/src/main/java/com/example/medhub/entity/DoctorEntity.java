package com.example.medhub.entity;

import com.example.medhub.dto.request.DoctorCreateRequestDto;
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
public class DoctorEntity {
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
    private LocationEntity locationEntity;

    @ManyToOne
    @JoinColumn(name = "specialization_id")
    private SpecializationEntity specializationEntity;

    public static DoctorEntity from(DoctorCreateRequestDto newDoctor) {
        return DoctorMapper.DOCTOR_MAPPER.toDoctor(newDoctor);
    }
}
