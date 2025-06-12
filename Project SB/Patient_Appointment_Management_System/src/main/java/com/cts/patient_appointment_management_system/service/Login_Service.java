package com.cts.patient_appointment_management_system.service;

import java.util.Optional;

import com.cts.patient_appointment_management_system.entity.Doctor;
import com.cts.patient_appointment_management_system.entity.Patient;

public interface Login_Service {
	 public Optional<Patient> authenticatePatient(String email, String password);
	 public Optional<Doctor> authenticateDoctor(String email, String password);
	 public boolean isAdmin(String email, String password);

}
