package com.example.medhub.entity;

import com.example.medhub.entity.keys.DoctorSpecializationId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "doctor_specializations")
public class DoctorSpecialization {

    @EmbeddedId
    private DoctorSpecializationId doctorSpecializationId;

}
