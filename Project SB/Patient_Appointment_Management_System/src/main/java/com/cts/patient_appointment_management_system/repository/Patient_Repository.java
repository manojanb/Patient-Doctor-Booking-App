package com.cts.patient_appointment_management_system.repository;

import java.util.Optional;

import org.springframework.data.annotation.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.patient_appointment_management_system.entity.Patient;
@Repository

public interface Patient_Repository extends JpaRepository<Patient, Id>  {

	Optional<Patient> findByEmail(String email);
	boolean existsByEmail(String email);
	Patient findByPatientId(String patientId);
	

}
