package com.cts.patient_appointment_management_system.repository;


import java.time.LocalDate;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.patient_appointment_management_system.entity.Appointment;
@Repository
public interface Appointment_Repository extends JpaRepository<Appointment, String>  {

	

	
	List<Appointment> findByPatient_PatientId(String patientId);
	List<Appointment> findByDoctor_DoctorId(String doctorId);
	List<Appointment> findByStatus(boolean b);
	List<Appointment> findByDoctor_DoctorIdAndStatus(String doctorId,boolean b);
	List<Appointment> findByPatient_PatientIdAndStatus(String patientId,boolean b);
	List<Appointment> findByDoctor_DoctorIdAndAppointmentDateAndAppointmentTimeAndStatus(String doctorId, LocalDate appointmentDate, String appointmentTime,boolean b);


	Appointment findByAppointmentId(String appointmentId);
	Appointment findByAppointmentIdAndStatus(String appointmentId, boolean b);
	
	
	
	
	

}
