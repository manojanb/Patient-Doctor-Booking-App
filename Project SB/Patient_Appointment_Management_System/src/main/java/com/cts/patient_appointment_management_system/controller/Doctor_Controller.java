package com.cts.patient_appointment_management_system.controller;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.cts.patient_appointment_management_system.entity.Appointment;
import com.cts.patient_appointment_management_system.entity.Doctor;
import com.cts.patient_appointment_management_system.service.Appointment_Service;
import com.cts.patient_appointment_management_system.service.Doctor_Service;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("userdoctor")
@RequestMapping("/doctor")
public class Doctor_Controller {
	
	
	
	
	@Autowired
	private Doctor_Service service;
	 @Autowired
		private Appointment_Service appservice;
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/doctor123")
	public  String showdoctor(Model model) {
		
	 Doctor doctor=new Doctor();
		model.addAttribute("doctor", doctor);
	
		return "doctorreg";
		
		
	}
	@PostMapping("/savedoctor")
	public String saveDoctor( @Valid @ModelAttribute("doctor") Doctor doctor, BindingResult result,Model model) {
		if (result.hasErrors()) {
	        model.addAttribute("errorMessage", "Invalid input data. Please check your fields.");
	        return "doctorreg";
	    }
		
		doctor.setStatus(false);
		String encodedPassword=passwordEncoder.encode(doctor.getPassword());
		doctor.setPassword(encodedPassword);
		
		
		if(service.ByEmailuni(doctor.getEmail())==false) {
			String errorMsg = "Email Id is already registered . Please try again.";
		       model.addAttribute("errorMessage", errorMsg);
		       return "doctorreg";
		}
		
		
		service.save(doctor);
		return "regsuccess";
	}
	
	
	@GetMapping("/dashboard")
	public String doctordashboard(HttpSession session) {
		if (!"DOCTOR".equals(session.getAttribute("role"))) {
		return "redirect:/login";
		}
		return "doctordash";
	}
		
		
		@GetMapping("/access")
		public String access(Doctor doctor) {
			
				return "login";
			}
		
		@GetMapping("/MarkasCompleted/{appointmentId}")
		public String markcompleted(@PathVariable String  appointmentId, HttpSession session) {
			Optional<Appointment>mark=appservice.findById(appointmentId);
			
			if(mark.isPresent()) {
				mark.get().setStatus(true);
				appservice.save(mark.get());
			    session.setAttribute("markcompleted", appservice.findByDoctor_DoctorIdAndStatus(mark.get().getDoctor().getDoctorId(),true));
				}
			return"redirect:/doctor/showfalse";
		}
		
		@GetMapping("/showfalse")
		public String showfalse(HttpSession session,@SessionAttribute("userdoctor") Doctor doc) {
			session.setAttribute("appointmentapprove", appservice.findByDoctor_DoctorIdAndStatus(doc.getDoctorId(), false));
			return "doctordash";
			
		}

	

}
