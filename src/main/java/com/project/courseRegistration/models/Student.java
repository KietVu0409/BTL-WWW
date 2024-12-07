package com.project.courseRegistration.models;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "students")
public class Student {
	@Id
	private Long id;
	
	@Column(name = "name", nullable = false, length = 50)
	private String name;
	
	@Column(name = "gender", nullable = false)
	private String gender;
	
	@Column(name = "address", length = 100)
	private String address;
	
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "phone", length = 20)
	private String phone;
	
	@Column(name = "semester", nullable = false)
	private int semester;
	
	@ManyToOne
	@JoinColumn(name = "major_id", nullable = false)
	private Major major;
	
	@Column(name = "date_of_birth")
	private Date dateOfBirth;

//	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
//	@JsonIgnore
//	Collection<RegistrationDetails> registrationDetails;
}
