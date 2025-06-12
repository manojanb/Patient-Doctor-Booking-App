package com.cts.patient_appointment_management_system.controller;


import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cts.patient_appointment_management_system.entity.Doctor;
import com.cts.patient_appointment_management_system.entity.Patient;
import com.cts.patient_appointment_management_system.service.Appointment_Service;
import com.cts.patient_appointment_management_system.service.Doctor_Service;
import com.cts.patient_appointment_management_system.service.Login_Service;
import com.cts.patient_appointment_management_system.service.Patient_Service;

import jakarta.servlet.http.HttpSession;



@Controller
public class Home_Controller {

    @Autowired
    private Login_Service loginService;
    
    @Autowired
    private Doctor_Service docService;
    
    @Autowired
    private Patient_Service patService;
    
    @Autowired
    private Appointment_Service appService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @PostMapping("/savelogin")
    public String login(@RequestParam String email, 
    		@RequestParam String password, Model model, HttpSession session) {
    	
        Optional<Patient> patient = loginService.authenticatePatient(email, password);
        if (patient.isPresent()) {
            session.setAttribute("userpatient", patient.get());
            session.setAttribute("role", "PATIENT");

            String patientId = patient.get().getPatientId();
            session.setAttribute("viewappointment", appService.findByPatient_PatientIdAndStatus(patientId, false));
            session.setAttribute("markcompleted", appService.findByPatient_PatientIdAndStatus(patientId, true));

            return "redirect:/patient/dashboard";
        }

        Optional<Doctor> doctor = loginService.authenticateDoctor(email, password);
        if (doctor.isPresent()) {
            session.setAttribute("userdoctor", doctor.get());
            session.setAttribute("role", "DOCTOR");

            String doctorId = doctor.get().getDoctorId();
            session.setAttribute("appointmentapprove", appService.findByDoctor_DoctorIdAndStatus(doctorId, false));
            session.setAttribute("markcompleted", appService.findByDoctor_DoctorIdAndStatus(doctorId, true));

            if (!doctor.get().isStatus()) {
                return "access-denied";
            }
            return "redirect:/doctor/dashboard";
        }

       
        if (loginService.isAdmin(email, password)) {
            session.setAttribute("managedoctor", docService.findByStatus(false));
            session.setAttribute("alldoctor", docService.findByStatus(true));
            session.setAttribute("viewall", patService.findAll());
            session.setAttribute("allviewappointment", appService.findAllAppointments());

            model.addAttribute("Total", appService.findAllAppointments().size());
            model.addAttribute("Completed", appService.findByStatus(true).size());
            model.addAttribute("Pending", appService.findByStatus(false).size());

            return "admindash";
        }

        model.addAttribute("errorMessage", "Invalid Email or Password. Please try again.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}
