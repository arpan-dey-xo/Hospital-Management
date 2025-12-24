package com.hospital.hospital_management.service;

import com.hospital.hospital_management.entity.Doctor;
import com.hospital.hospital_management.enums.Specialization;
import com.hospital.hospital_management.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DoctorService {

    private final DoctorRepository doctorRepository;

    // Create / Update
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Read all
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Read by ID
    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    // Delete
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }

    // Custom query example
    public List<Doctor> getDoctorsBySpecialization(Specialization specialization) {
        return doctorRepository.findBySpecialization(specialization);
    }
}
