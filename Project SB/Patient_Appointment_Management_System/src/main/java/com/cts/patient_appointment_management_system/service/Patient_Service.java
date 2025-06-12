package com.cts.patient_appointment_management_system.service;

import java.util.List;
import java.util.Optional;

import com.cts.patient_appointment_management_system.entity.Patient;

public interface Patient_Service {
	public Patient save(Patient patient);
	public boolean ByEmailuni(String email);
	public Optional<Patient> findByEmail(String email);
	public List<Patient> findAll();
	public Patient findByPatientId(String patientId);

}
