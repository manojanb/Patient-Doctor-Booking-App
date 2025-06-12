package com.cts.patient_appointment_management_system.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.patient_appointment_management_system.entity.Doctor;
import com.cts.patient_appointment_management_system.service.Doctor_Service;
import jakarta.servlet.http.HttpSession;


@Controller

public class Admin_Controller {
	
	@Autowired
	private Doctor_Service docservice;

	
	
	@GetMapping("/admin")
	public String admin( ) {
		return "login";
		}         
	
	@GetMapping("/approve/{doctorId}")
	public String approve(@PathVariable String doctorId,HttpSession session) {
		Optional<Doctor>approve=docservice.findById(doctorId);
		if(approve.isPresent()) {
			approve.get().setStatus(true);
			docservice.save(approve.get());
    		session.setAttribute("alldoctor", docservice.findByStatus(true));
    		}
		return "redirect:/showfalse";
	
	}
	
	@GetMapping("/remove/{doctorId}")
	public String remove(@PathVariable String doctorId) {
			docservice.Delete(doctorId);
			return "redirect:/showfalse";
	
	}
	
	
	@GetMapping("/showfalse")
	public String showfalse(HttpSession session) {
		session.setAttribute("managedoctor", docservice.findByStatus(false));
		return "admindash";
		
	}
	
	
		
	
}
	 

