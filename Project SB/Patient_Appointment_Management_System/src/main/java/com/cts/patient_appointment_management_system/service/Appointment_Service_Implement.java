package com.cts.patient_appointment_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.patient_appointment_management_system.entity.Appointment;
import com.cts.patient_appointment_management_system.repository.Appointment_Repository;
@Service
public class Appointment_Service_Implement implements Appointment_Service {
	@Autowired 
	private Appointment_Repository repo;

	@Override
	public List<Appointment> findByPatient_PatientId(String patientId) {
	
		return repo.findByPatient_PatientId(patientId);
	}

	@Override
	public List<Appointment> findByStatus(boolean b) {
		
		return repo.findByStatus(b);
	}

	@Override
	public List<Appointment> findByDoctor_DoctorIdAndStatus(String doctorId, boolean b) {
	
		return repo.findByDoctor_DoctorIdAndStatus(doctorId, b);
	}

	@Override
	public Optional<Appointment> findById(String appointmentId) {
		
		return repo.findById(appointmentId);

}

	@Override
	public Appointment save(Appointment appointment) {
		
		
		return repo.save(appointment);
	}

	@Override
	public Appointment findByAppointmentId(String appointmentId) {
		
		return repo.findByAppointmentId(appointmentId);
	}

	@Override
	public void Delete(Appointment app) {
		
		
		repo.delete(app);
		
	}

	@Override
	public List<Appointment> findAllAppointments() {

		return repo.findAll();
	}

	
	

	@Override
	public List<Appointment> findByPatient_PatientIdAndStatus(String patientId, boolean b) {
		
		return repo.findByPatient_PatientIdAndStatus(patientId, b);
	}

	@Override
	public Appointment findByAppointmentIdAndStatus(String appointmentId, boolean b) {
		
		return repo.findByAppointmentIdAndStatus(appointmentId, b);
	}

	@Override
	public boolean isAlreadyBooked(String doctorId, LocalDate appointmentDate, String appointmentTime,boolean b) {
		List<Appointment> booked= repo.findByDoctor_DoctorIdAndAppointmentDateAndAppointmentTimeAndStatus(doctorId, appointmentDate, appointmentTime,b);	
		return !booked.isEmpty();
	}

	}
