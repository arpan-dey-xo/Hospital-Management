package com.hospital.hospital_management.repository;

import com.hospital.hospital_management.entity.Appointment;
import com.hospital.hospital_management.enums.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate date);

    List<Appointment> findByPatientId(Long patientId);

    @Query("SELECT a FROM Appointment a WHERE a.appointmentDate >= CURRENT_DATE")
    List<Appointment> findUpcomingAppointments();

    List<Appointment> findByAppointmentTimeBetween(LocalTime start, LocalTime end);

    long countByStatus(AppointmentStatus status);
}
