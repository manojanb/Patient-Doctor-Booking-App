package com.cts.patient_appointment_management_system.service;

import java.util.List;
import java.util.Optional;

import com.cts.patient_appointment_management_system.entity.Doctor;


public interface Doctor_Service {
	public Doctor save(Doctor doctor);
	public boolean ByEmailuni(String email);
	public Optional<Doctor> findByEmail(String email);
	public List<Doctor> findByStatus(boolean b);
	public Doctor findByName(String selectdoctor);
	public Optional<Doctor> findById(String doctorId);
public void Delete(String doctorId);


}
