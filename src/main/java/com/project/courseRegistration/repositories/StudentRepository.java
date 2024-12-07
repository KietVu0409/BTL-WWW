package com.project.courseRegistration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.courseRegistration.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
