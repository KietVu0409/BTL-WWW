package com.project.courseRegistration.services;

import com.project.courseRegistration.dtos.StudentDTO;
import com.project.courseRegistration.models.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents();

    Student getStudentById(Long id);

    Student insertStudent(StudentDTO studentDTO);

    Student updateStudent(StudentDTO studentDTO);

    void userUpdate(StudentDTO studentDTO);

    void deleteStudent(Long id);
}
