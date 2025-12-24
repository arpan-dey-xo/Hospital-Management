package com.hospital.hospital_management.entity;

import com.hospital.hospital_management.embedded.Address;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DoctorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;

    private int experience;

    @Embedded
    private Address address;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
}
