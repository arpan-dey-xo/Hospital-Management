package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

    @Query("SELECT p FROM Prescription p JOIN p.medicines m WHERE m.name = :medicineName")
    List<Prescription> findByMedicineName(String medicineName);

    @Query("""
        SELECT p FROM Prescription p
        JOIN p.appointment a
        WHERE a.patient.id = :patientId
    """)
    List<Prescription> findByPatientId(Long patientId);

    @Query("""
        SELECT DISTINCT p2 FROM Prescription p
        JOIN p.appointment a
        JOIN a.patient p2
        WHERE a.doctor.id = :doctorId
    """)
    List<Object> findPatientsByDoctor(Long doctorId);

    @Query("""
        SELECT m.name, COUNT(m)
        FROM Prescription p
        JOIN p.medicines m
        GROUP BY m.name
        ORDER BY COUNT(m) DESC
    """)
    List<Object[]> findMostPrescribedMedicines();
}
