package com.project.courseRegistration.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

}
