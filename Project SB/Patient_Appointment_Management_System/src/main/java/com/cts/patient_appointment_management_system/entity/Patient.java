package com.cts.patient_appointment_management_system.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
@Entity

public class Patient {

	    @Id
	    private String patientId;

	    @PrePersist
	    public void generatePatientId() {
	    	 String prefix = "PAT-";
	    	    int randomNum = new Random().nextInt(9999);
	    	    
	    	    this.patientId = prefix + "-" + randomNum;
	    }
	


	@NotBlank(message = "Name cannot be blank")
	@Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
	private String name;

	@NotBlank(message = "Gender cannot be blank")
	private String gender;

	@Email(message = "Invalid email format")
	@NotBlank(message = "Email cannot be empty")
	@Column(unique = true)
	private String email;

	
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format")
	@NotBlank(message = "Phone number cannot be empty")
	private String phone;

	@NotBlank(message = "Address cannot be blank")
	private String address;

	@Min(value = 0, message = "Age cannot be negative")
	@Max(value = 120, message = "Age should be realistic")
	private int age;

	@Size(min = 8, message = "Password must be at least 8 characters long")
	@Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$", 
	         message = "Password must contain at least one uppercase letter and one number")
	private String password;

	@Transient
	private String confirmpassword;

	@OneToMany(mappedBy="patient",cascade=CascadeType.ALL)
	private List<Appointment>appointments=new ArrayList<>();

}
