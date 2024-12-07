package com.project.courseRegistration.controllers;

import com.project.courseRegistration.dtos.RDetailDto;
import com.project.courseRegistration.models.Course;
import com.project.courseRegistration.models.RegistrationDetails;
import com.project.courseRegistration.services.RegistrationDetailService;
import com.project.courseRegistration.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationDetailController {
    private final StudentService studentService;
    private final RegistrationDetailService detailService;

    @GetMapping("/show/{id}")
    public String registrationDetail(Model model,@PathVariable Long id) {
        try {
            List<Course> courses = detailService.getCourseListForUser(id);
            model.addAttribute("student", studentService.getStudentById(id));
            model.addAttribute("courses", courses);
            return "web/student/registration";
        }catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @GetMapping("/enroll/{id}")
    public String enrollmentDetail(Model model,@PathVariable Long id) {
        try {
            List<RegistrationDetails> registrationDetails = detailService.getEnroll(id);
            model.addAttribute("registrationDetails", registrationDetails);
            return "web/student/course";
        }catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }

    @PostMapping("/user/add")
    public String addRegistrationDetail(Model model,
                                        RDetailDto rDetailDto) {
        try {
            detailService.addCourseForStudent(rDetailDto);
            return "redirect:/registration/show/" + rDetailDto.getStudent_id();
        }catch (Exception e) {
            model.addAttribute("error",e.getMessage());
            return "error";
        }
    }
}
