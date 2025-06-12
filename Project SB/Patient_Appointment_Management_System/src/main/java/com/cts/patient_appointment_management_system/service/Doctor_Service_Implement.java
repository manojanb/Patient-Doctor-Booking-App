package com.cts.patient_appointment_management_system.service;



import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.patient_appointment_management_system.entity.Doctor;
import com.cts.patient_appointment_management_system.repository.Doctor_Repository;
import com.cts.patient_appointment_management_system.repository.Patient_Repository;
@Service
public class Doctor_Service_Implement implements Doctor_Service {
@Autowired
 private Doctor_Repository repository;

@Autowired
private Patient_Repository patie;
	@Override
		public Doctor save(Doctor doctor) {
			
			return repository.save(doctor) ;
		}
	@Override
	public boolean ByEmailuni(String email) {
		if(repository.existsByEmail(email)|| patie.existsByEmail(email)) {
		return false;
	}return true;
	}
	@Override
	public Optional<Doctor> findByEmail(String email) {
		
		return repository.findByEmail(email);
	}
	@Override
	public List<Doctor> findByStatus(boolean b) {
	
		return repository.findByStatus(b);
	}
	@Override
	public Doctor findByName(String selectdoctor) {
		
		return repository.findByName(selectdoctor);
	}
	@Override
	public Optional<Doctor> findById(String doctorId) {
		
		return repository.findByDoctorId(doctorId);
	}
	@Override
	public void Delete(String doctorId) {
		
		Optional<Doctor>doc= repository.findByDoctorId(doctorId);
		
		repository.delete(doc.get());
		
		
	}
	
	
	
	
	
	
	
	
}
