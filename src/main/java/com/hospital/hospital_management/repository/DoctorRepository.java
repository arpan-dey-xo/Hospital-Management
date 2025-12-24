package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.entity.Doctor;
import com.hospital.hospital_management.enums.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // Derived query: Find doctors by specialization
    List<Doctor> findBySpecialization(Specialization specialization);

    // Custom query example: count appointments per doctor (will join with Appointment)
    @Query("SELECT d FROM Doctor d WHERE d.name LIKE %:name%")
    List<Doctor> findDoctorsByNameContains(String name);
}
