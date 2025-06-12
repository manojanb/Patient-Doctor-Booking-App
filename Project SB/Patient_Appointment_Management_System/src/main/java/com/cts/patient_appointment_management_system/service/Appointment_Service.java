package com.cts.patient_appointment_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.cts.patient_appointment_management_system.entity.Appointment;

public interface Appointment_Service {

	List<Appointment> findByPatient_PatientId(String patientId);

	List<Appointment> findByStatus(boolean b);

	List<Appointment> findByDoctor_DoctorIdAndStatus(String doctorId, boolean b);

	Optional<Appointment> findById(String appointmentId);

	Appointment save(Appointment appointment);

	Appointment findByAppointmentId(String appointmentId);

    

	void Delete(Appointment app);

	List<Appointment> findAllAppointments();


	List<Appointment> findByPatient_PatientIdAndStatus(String patientId, boolean b);

	Appointment findByAppointmentIdAndStatus(String appointmentId, boolean b);
	public boolean isAlreadyBooked(String doctorId, LocalDate appointmentDate, String appointmentTime,boolean b);
	

}
