package com.project.courseRegistration.services.implement;

import com.project.courseRegistration.dtos.TeacherDTO;
import com.project.courseRegistration.models.Teacher;
import com.project.courseRegistration.repositories.TeacherRepository;
import com.project.courseRegistration.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    }

    @Override
    public Teacher addTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findByName(teacherDTO.getName());
        if (teacher != null) {
            throw new RuntimeException("Teacher already exists");
        }
        teacher = Teacher.builder()
                .name(teacherDTO.getName())
                .build();
        return teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = teacherRepository.findById(teacherDTO.getId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        teacher.setName(teacherDTO.getName());
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        teacher.ifPresent(teacherRepository::delete);
    }
}
