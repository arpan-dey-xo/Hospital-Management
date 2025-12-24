package com.hospital.hospital_management.entity;

import com.hospital.hospital_management.enums.Specialization;
import jakarta.persistence.*;
        import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    @OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
    private DoctorDetail doctorDetail;
}
