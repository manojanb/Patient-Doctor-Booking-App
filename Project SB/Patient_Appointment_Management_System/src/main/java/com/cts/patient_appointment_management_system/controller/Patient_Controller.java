package com.cts.patient_appointment_management_system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.patient_appointment_management_system.entity.Patient;
import com.cts.patient_appointment_management_system.service.Doctor_Service;
import com.cts.patient_appointment_management_system.service.Patient_Service;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
@RequestMapping("/patient")
public class Patient_Controller {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private Patient_Service patservice;
	 @Autowired
	 private Doctor_Service docservice;
	 
	 
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/patient123")
	public  String showPatient(Model model) {
	    Patient patient=new Patient();
		model.addAttribute("patient", patient);
		return "patientreg";
		}
	@PostMapping("/savepatient")
	public String savePatient(@Valid @ModelAttribute("patient") Patient patient, BindingResult result, Model model) {
	    
		if (result.hasErrors()) {
	        model.addAttribute("errorMessage", "Invalid input data. Please check your fields.");
	        return "patientreg";
	    }
		
		if (!patservice.ByEmailuni(patient.getEmail())) {
	        model.addAttribute("errorMessage", "Email ID is already registered. Please try again.");
	        return "patientreg";
	    }
		
	    if (!patient.getPassword().equals(patient.getConfirmpassword())) {
	        model.addAttribute("errorMessage", "Passwords do not match!");
	        return "patientreg"; 
	    }
	    patient.setPassword(passwordEncoder.encode(patient.getPassword()));
	    patservice.save(patient);
	    return "redirect:/login";
	}

	
	@GetMapping("/dashboard")
	public String patientdashboard(HttpSession session ,Model model) {
		if (!"PATIENT".equals(session.getAttribute("role"))) {
		return "redirect:/login";
		}
		session.setAttribute("doctors", docservice.findByStatus(true));
		return "patientdash";
	}
	

	
}
