package com.project.courseRegistration.services.implement;

import com.project.courseRegistration.dtos.RDetailDto;
import com.project.courseRegistration.models.*;
import com.project.courseRegistration.repositories.CourseRepository;
import com.project.courseRegistration.repositories.RegistrationDetailsRepository;
import com.project.courseRegistration.repositories.StudentRepository;
import com.project.courseRegistration.repositories.SubjectRepository;
import com.project.courseRegistration.services.RegistrationDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationDetailServiceImpl implements RegistrationDetailService {
    private final CourseRepository courseRepository;

    private final StudentRepository studentRepository;

    private final RegistrationDetailsRepository detailsRepository;

    @Override
    public List<Course> getCourseListForUser(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return courseRepository.findSubjectForUser(student.getSemester(),
                student.getMajor().getId(), Status.UNACCOMPLISHED);
    }

    @Override
    public void addCourseForStudent(RDetailDto rDetailDto) {
        Student student = studentRepository.findById(rDetailDto.getStudent_id())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(rDetailDto.getCourse_id())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        Date date = Date.from(zonedDateTime.toInstant());

        RegistrationDetails registrationDetails = new RegistrationDetails();
        course.getSubject().setStatus(Status.PROCESSING);
        registrationDetails.setStudent(student);
        registrationDetails.setCourse(course);
        registrationDetails.setRegistrationDate(date);

        detailsRepository.save(registrationDetails);
    }

    @Override
    public List<RegistrationDetails> getEnroll(Long id) {
        List<RegistrationDetails> registrationDetails = detailsRepository.findByStudent_Id(id);
        System.out.println(registrationDetails);
        return registrationDetails;
    }
}
