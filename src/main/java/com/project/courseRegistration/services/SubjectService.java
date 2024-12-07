package com.project.courseRegistration.services;

import com.project.courseRegistration.dtos.StudentDTO;
import com.project.courseRegistration.dtos.SubjectDTO;
import com.project.courseRegistration.models.Student;
import com.project.courseRegistration.models.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    List<Subject> findAll();

    Subject findById(Long id);

    Subject insertSubject(SubjectDTO subjectDTO);

    Subject updateSubject(SubjectDTO subjectDTO);

    void deleteSubject(Long id);
}
