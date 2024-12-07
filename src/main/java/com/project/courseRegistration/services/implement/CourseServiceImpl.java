package com.project.courseRegistration.services.implement;

import com.project.courseRegistration.dtos.CourseDTO;
import com.project.courseRegistration.models.Course;
import com.project.courseRegistration.models.Subject;
import com.project.courseRegistration.models.Teacher;
import com.project.courseRegistration.repositories.CourseRepository;
import com.project.courseRegistration.repositories.SubjectRepository;
import com.project.courseRegistration.repositories.TeacherRepository;
import com.project.courseRegistration.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
    }

    @Override
    public void insertCourse(CourseDTO courseDTO) {
        Course course = courseRepository.findByName(courseDTO.getName());
        Subject subject = subjectRepository.findById(courseDTO.getSubject_id())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        Teacher teacher = teacherRepository.findById(courseDTO.getTeacher_id())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        if (course != null) {
            throw new RuntimeException("Course already exists");
        }
        Course newCourse = Course.builder()
                .name(courseDTO.getName())
                .limitedNumber(courseDTO.getLimitedNumber())
                .subject(subject)
                .teacher(teacher)
                .build();
        courseRepository.save(newCourse);
    }

    @Override
    public void updateCourse(CourseDTO courseDTO) {
        Course course = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        Subject subject = subjectRepository.findById(courseDTO.getSubject_id())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        Teacher teacher = teacherRepository.findById(courseDTO.getTeacher_id())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
        course.setName(courseDTO.getName());
        course.setLimitedNumber(courseDTO.getLimitedNumber());
        course.setSubject(subject);
        course.setTeacher(teacher);
        courseRepository.save(course);
    }

    @Override
    public void deleteCourse(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        course.ifPresent(courseRepository::delete);
    }
}
