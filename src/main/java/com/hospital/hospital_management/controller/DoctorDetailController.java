package com.hospital.hospital_management.controller;

import com.hospital.hospital_management.entity.DoctorDetail;
import com.hospital.hospital_management.service.DoctorDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctor-details")
@RequiredArgsConstructor
public class DoctorDetailController {

    private final DoctorDetailService doctorDetailService;

    // Create
    @PostMapping
    public ResponseEntity<DoctorDetail> createDoctorDetail(@Valid @RequestBody DoctorDetail doctorDetail) {
        return ResponseEntity.ok(doctorDetailService.saveDoctorDetail(doctorDetail));
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<DoctorDetail>> getAllDoctorDetails() {
        return ResponseEntity.ok(doctorDetailService.getAllDoctorDetails());
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<DoctorDetail> getDoctorDetailById(@PathVariable Long id) {
        return doctorDetailService.getDoctorDetailById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<DoctorDetail> updateDoctorDetail(@PathVariable Long id, @Valid @RequestBody DoctorDetail doctorDetail) {
        return doctorDetailService.getDoctorDetailById(id)
                .map(existingDetail -> {
                    doctorDetail.setId(id);
                    return ResponseEntity.ok(doctorDetailService.saveDoctorDetail(doctorDetail));
                }).orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorDetail(@PathVariable Long id) {
        doctorDetailService.deleteDoctorDetail(id);
        return ResponseEntity.noContent().build();
    }

    // Example custom query: Find DoctorDetails with experience >= X years
    @GetMapping("/experience/{years}")
    public ResponseEntity<List<DoctorDetail>> getDoctorDetailsByExperience(@PathVariable int years) {
        return ResponseEntity.ok(doctorDetailService.findByExperienceGreaterThanEqual(years));
    }
}
