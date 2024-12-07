package com.project.courseRegistration.models;

import jakarta.persistence.*;
import lombok.*;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@Entity
@Table(name = "prerequisites", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"subject_id", "prerequisite_id"})
})
public class Prerequisite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "prerequisite_id")
    private Subject prerequisite;
}
