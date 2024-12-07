package com.project.courseRegistration.services.implement;

import com.project.courseRegistration.dtos.StudentDTO;
import com.project.courseRegistration.models.Major;
import com.project.courseRegistration.models.Student;
import com.project.courseRegistration.repositories.MajorRepository;
import com.project.courseRegistration.repositories.StudentRepository;
import com.project.courseRegistration.services.StudentService;
import com.project.courseRegistration.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final MajorRepository majorRepository;
    private  final UserService userService;

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Student with id " + id + " not found")));
    }

    @Transactional
    @Override
    public Student insertStudent(StudentDTO studentDTO) {
        Optional<Student> student = studentRepository.findById(studentDTO.getId());
        Major major = majorRepository.findById(studentDTO.getMajor_id())
                .orElseThrow(() -> new RuntimeException("Major not found"));
        if (student.isEmpty()) {

            Student newStudent = Student.builder()
                    .id(studentDTO.getId())
                    .name(studentDTO.getName())
                    .email(studentDTO.getEmail())
                    .address(studentDTO.getAddress())
                    .phone(studentDTO.getPhone())
                    .gender(studentDTO.getGender())
                    .dateOfBirth(studentDTO.getDateOfBirth())
                    .semester(studentDTO.getSemester())
                    .major(major)
                    .build();
            userService.saveUser(studentDTO);
            return studentRepository.save(newStudent);
        }
        return null;
    }

    @Override
    public Student updateStudent(StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Major major = majorRepository.findById(studentDTO.getMajor_id())
                .orElseThrow(() -> new RuntimeException("Major not found"));
        student.setId(student.getId());
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());
        student.setPhone(studentDTO.getPhone());
        student.setGender(studentDTO.getGender());
        student.setDateOfBirth(studentDTO.getDateOfBirth());
        student.setSemester(studentDTO.getSemester());
        student.setMajor(major);
        return studentRepository.save(student);
    }

    @Override
    public void userUpdate(StudentDTO studentDTO) {
        Student student = studentRepository.findById(studentDTO.getId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());
        student.setPhone(studentDTO.getPhone());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        optionalStudent.ifPresent(studentRepository::delete);
    }
}
