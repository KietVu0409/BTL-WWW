package com.project.courseRegistration.repositories;

import com.project.courseRegistration.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher findByName(String name);
}
