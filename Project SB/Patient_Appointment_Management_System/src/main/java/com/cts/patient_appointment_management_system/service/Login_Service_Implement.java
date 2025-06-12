package com.cts.patient_appointment_management_system.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.patient_appointment_management_system.entity.Doctor;
import com.cts.patient_appointment_management_system.entity.Patient;

@Service
public class Login_Service_Implement implements Login_Service {
	
	
	 @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @Autowired
	    private Doctor_Service docService;
	    
	    @Autowired
	    private Patient_Service patService;

	    public Optional<Patient> authenticatePatient(String email, String password) {
	        Optional<Patient> patient = patService.findByEmail(email);
	        if (patient.isPresent() && passwordEncoder.matches(password, patient.get().getPassword())) {
	            return patient;
	        }
	        return Optional.empty();
	    }

	    public Optional<Doctor> authenticateDoctor(String email, String password) {
	        Optional<Doctor> doctor = docService.findByEmail(email);
	        if (doctor.isPresent() && passwordEncoder.matches(password, doctor.get().getPassword())) {
	            return doctor;
	        }
	        return Optional.empty();
	    }

	    public boolean isAdmin(String email, String password) {
	        return email.equals("admin123@gmail.com") && password.equals("admin123@");
	    }
	}


