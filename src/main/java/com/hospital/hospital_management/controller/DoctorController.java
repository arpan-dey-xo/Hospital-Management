package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.entity.Doctor;
import com.hospital.hospital_management.enums.Specialization;
import com.hospital.hospital_management.service.DoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // Create
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor) {
        return ResponseEntity.ok(doctorService.saveDoctor(doctor));
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        return doctorService.getDoctorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @Valid @RequestBody Doctor doctor) {
        return doctorService.getDoctorById(id)
                .map(existingDoctor -> {
                    doctor.setId(id);
                    return ResponseEntity.ok(doctorService.saveDoctor(doctor));
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }

    // Custom query example: doctors by specialization
    @GetMapping("/specialization/{spec}")
    public ResponseEntity<List<Doctor>> getDoctorsBySpecialization(@PathVariable Specialization spec) {
        return ResponseEntity.ok(doctorService.getDoctorsBySpecialization(spec));
    }
}
