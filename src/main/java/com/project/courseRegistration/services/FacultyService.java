package com.project.courseRegistration.services;

import java.util.List;

import com.project.courseRegistration.dtos.FacultyDTO;
import com.project.courseRegistration.models.Faculty;

public interface FacultyService {

	List<Faculty> getFaculties();

    Faculty getFacultyById(Long id);

    Faculty insertFaculty(FacultyDTO facultyDTO);

    void deleteFaculty(Long id);

    void updateFaculty(FacultyDTO facultyDTO);
}
