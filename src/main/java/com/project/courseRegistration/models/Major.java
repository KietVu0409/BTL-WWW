package com.project.courseRegistration.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data @Builder
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "majors")
public class Major {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 50)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "faculty_id")
	private Faculty faculty;
}
