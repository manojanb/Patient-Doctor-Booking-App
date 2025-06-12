package com.cts.patient_appointment_management_system.controller;


import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.patient_appointment_management_system.entity.Appointment;
import com.cts.patient_appointment_management_system.entity.Doctor;
import com.cts.patient_appointment_management_system.entity.Patient;
import com.cts.patient_appointment_management_system.service.Appointment_Service;
import com.cts.patient_appointment_management_system.service.Doctor_Service;
import com.cts.patient_appointment_management_system.service.Patient_Service;
import jakarta.servlet.http.HttpSession;

@Controller
@SessionAttributes("userpatient")


public class Appointment_Controller {
	@Autowired
	private Appointment_Service appservice;
	@Autowired 
	private Patient_Service patservice;
	@Autowired 
	private Doctor_Service docservice;
	
	@GetMapping("/appointment123")
	public  String showPatient(Model model) {
		model.addAttribute("appointment", new Appointment());
		return "patientdash";
	}
	
	@PostMapping("/appointment")
	public String SaveAppointment(Appointment appointment,
			HttpSession session,
			@SessionAttribute("userpatient") Patient userpatient,
			@RequestParam("select_doctor") String selectdoctor,
			@RequestParam("appointmentDate") LocalDate appointmentDate,
            @RequestParam("appointmentTime") String appointmentTime,
            Model model) {
		
		String patientId=userpatient.getPatientId();
		appointment.setPatient(patservice.findByPatientId(patientId));
		appointment.setName(userpatient.getName());
		appointment.setStatus(false);
		
		Doctor selecteddoctor=docservice.findByName(selectdoctor);
		appointment.setDoctor(selecteddoctor);
		
		boolean isBooked = appservice.isAlreadyBooked(selecteddoctor.getDoctorId(), appointmentDate, appointmentTime,false);
	       
		if (isBooked) {
			model.addAttribute("errorMessage", "This appointment is already booked.");
            return "patientdash";
        }
		
		appservice.save(appointment);
        session.setAttribute("viewappointment",appservice.findByPatient_PatientIdAndStatus(patientId,false));
		return "patientdash";
	}
	
	

	@PostMapping("/reschedule/{appointmentId}")
	public String reschedule(@PathVariable String appointmentId,
	                         @RequestParam("appointmentDate") LocalDate appointmentDate,
	                         @RequestParam("appointmentTime") String appointmentTime,
	                         @RequestParam("select_doctor") String selectDoctor,
	                         HttpSession session,@SessionAttribute("userpatient") Patient userpatient) {
	    
	   
	    Appointment existingAppointment = appservice.findByAppointmentId(appointmentId);
	    
	    if (existingAppointment != null) {
	      
	        existingAppointment.setAppointmentDate(appointmentDate);
	        existingAppointment.setAppointmentTime(appointmentTime);
	        existingAppointment.setSelect_doctor(selectDoctor);
	        
	        Doctor doctordetails=docservice.findByName(selectDoctor);
	        existingAppointment.setDoctor(doctordetails);

	      
	        appservice.save(existingAppointment);
	        String patientId=userpatient.getPatientId();
	        session.setAttribute("viewappointment", appservice.findByPatient_PatientIdAndStatus(patientId,false));
	    }

	    return "patientdash";
	}
	  
	 
	 @GetMapping("/cancel/{appointmentId}")
	  public String cancel(@PathVariable String appointmentId ,HttpSession session) {
		 Patient userpatient = (Patient) session.getAttribute("userpatient");
		  
		 Appointment app= appservice.findByAppointmentId(appointmentId);
		 appservice.Delete(app);
		 
		 String patientId=userpatient.getPatientId();
		 session.setAttribute("viewappointment",appservice.findByPatient_PatientIdAndStatus(patientId,false));
		 return"patientdash";
	  }
	
	

}
