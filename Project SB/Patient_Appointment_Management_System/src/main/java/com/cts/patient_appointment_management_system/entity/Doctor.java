package com.cts.patient_appointment_management_system.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
public class Doctor {

    @Id
    private String doctorId;

    @PrePersist
    public void generatePatientId() {
    	 String prefix = "DOC-";
    	   
    	    int randomNum = new Random().nextInt(9999);
    	    
    	    this.doctorId = prefix + "-" + randomNum;
    	    }

    @NotBlank(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "Specialization cannot be empty")
    private String specialization;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email cannot be empty")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format")
    @NotBlank(message = "Phone number cannot be empty")
    private String phone;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", 
             message = "Password must contain at least one uppercase letter and one number")
    private String password;

    @Transient
    private String confirmpassword;

    private boolean status;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    private List<Appointment> appointments = new ArrayList<>();
}
