package com.project.courseRegistration.repositories;

import com.project.courseRegistration.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.courseRegistration.models.Subject;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByName(String name);
}
