package com.project.courseRegistration.services;

import com.project.courseRegistration.dtos.RDetailDto;
import com.project.courseRegistration.models.Course;
import com.project.courseRegistration.models.RegistrationDetails;
import com.project.courseRegistration.models.Subject;

import java.util.List;

public interface RegistrationDetailService {

    List<Course> getCourseListForUser(Long id);

    void addCourseForStudent(RDetailDto rDetailDto);

    List<RegistrationDetails> getEnroll(Long id);
}
