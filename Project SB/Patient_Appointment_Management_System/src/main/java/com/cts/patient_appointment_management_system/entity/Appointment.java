package com.cts.patient_appointment_management_system.entity;

import java.time.LocalDate;
import java.util.Random;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Appointment {
	@Id
	private String appointmentId;
	@PrePersist
	public void generateappointmentId() {
		this.appointmentId="APP"+new Random().nextInt(1000);
	}
	private String name;
	@NotBlank(message = "Doctor selection is required")
	private String select_doctor;
	@FutureOrPresent(message = "Appointment date must be in the future")
	private LocalDate appointmentDate;
	@NotBlank(message = "Appointment time is required")
	private String appointmentTime;
	private boolean status;
	
	@ManyToOne
	@JoinColumn(name="patientId")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name="doctorId")
	private Doctor doctor;

	
	

}
