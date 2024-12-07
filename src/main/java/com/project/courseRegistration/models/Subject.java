package com.project.courseRegistration.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data @Builder
@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "subjects")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name",nullable = false, length = 100)
	private String name;
	
	@Column(name = "accredit", nullable = false)
	private int accredit;
	
	@Column(name = "semester", nullable = false)
	private int semester;
	
	@Column(name = "tuition", nullable = false)
	private double tuition;

	@Column(name = "status", nullable = false)
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "major_id", nullable = false)
	private Major major;
	
	@Column(name = "type")
	private String type;

	@OneToMany(mappedBy = "subject")
	private Set<Course> courses;

	@OneToMany(mappedBy = "subject")
	private Set<Prerequisite> prerequisites;

	@OneToMany(mappedBy = "prerequisite")
	private Set<Prerequisite> prerequisiteFor;
}
