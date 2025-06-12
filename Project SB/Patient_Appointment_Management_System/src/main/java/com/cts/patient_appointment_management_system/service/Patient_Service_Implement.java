package com.cts.patient_appointment_management_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.patient_appointment_management_system.entity.Patient;
import com.cts.patient_appointment_management_system.repository.Doctor_Repository;
import com.cts.patient_appointment_management_system.repository.Patient_Repository;

@Service
public class Patient_Service_Implement implements Patient_Service {
@Autowired
private Patient_Repository repository;
@Autowired
private Doctor_Repository docto;
	@Override
	public Patient save(Patient patient) {
		
		return repository.save(patient) ;
	}
	public boolean ByEmailuni(String email) {
		if(repository.existsByEmail(email)|| docto.existsByEmail(email)) {
			return false;
		}
		return true;
	}
	@Override
	public Optional<Patient> findByEmail(String email) {
		
		return repository.findByEmail(email);
	

}
	@Override
	public List<Patient> findAll() {
		
		return repository.findAll();
	}
	@Override
	public Patient findByPatientId(String patientId) {
		
		return repository.findByPatientId(patientId);
	}}
