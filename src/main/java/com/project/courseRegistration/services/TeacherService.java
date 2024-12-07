package com.project.courseRegistration.services;

import com.project.courseRegistration.dtos.TeacherDTO;
import com.project.courseRegistration.models.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Teacher getTeacherById(Long id);
    Teacher addTeacher(TeacherDTO teacherDTO);
    void updateTeacher(TeacherDTO teacherDTO);
    void deleteTeacher(Long id);
}
