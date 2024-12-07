package com.project.courseRegistration.services;

import com.project.courseRegistration.dtos.CourseDTO;
import com.project.courseRegistration.models.Course;

import java.util.List;

public interface CourseService {
    List<Course> findAll();
    Course findById(Long id);
    void insertCourse(CourseDTO courseDTO);
    void updateCourse(CourseDTO courseDTO);
    void deleteCourse(Long id);
}
