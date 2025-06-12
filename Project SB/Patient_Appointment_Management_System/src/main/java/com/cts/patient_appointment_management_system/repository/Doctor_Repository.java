package com.cts.patient_appointment_management_system.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.patient_appointment_management_system.entity.Doctor;
@Repository
public interface Doctor_Repository extends JpaRepository<Doctor, String> {

	Optional<Doctor> findByEmail(String email);
	Optional<Doctor> findByDoctorId(String doctorId);
	List<Doctor> findByStatus(boolean status);
	boolean existsByEmail(String email);
	Doctor findByName(String selectdoctor);
	
	
	

}
