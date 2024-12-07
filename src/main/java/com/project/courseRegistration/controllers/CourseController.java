package com.project.courseRegistration.controllers;

import com.project.courseRegistration.dtos.CourseDTO;
import com.project.courseRegistration.models.Course;
import com.project.courseRegistration.services.CourseService;
import com.project.courseRegistration.services.SubjectService;
import com.project.courseRegistration.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;
    private final TeacherService teacherService;
    private final SubjectService subjectService;

    @GetMapping("")
    public String show(Model model) {
        List<Course> courses = courseService.findAll();
        model.addAttribute("courses", courses);
        return "admin/courses/overview";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("subjects", subjectService.findAll());
        return "admin/courses/add";
    }

    @GetMapping("/modify")
    public String modify(Model model, Long id) {
        model.addAttribute("teachers", teacherService.getAllTeachers());
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("course", courseService.findById(id));
        return "admin/courses/update";
    }

    @PostMapping("/insert")
    public String insert(CourseDTO courseDTO,
                         BindingResult result,
                         Model model) {

        try {
            courseService.insertCourse(courseDTO);
            return "redirect:/course";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/update")
    public String update(CourseDTO courseDTO,
                         Model model) {
        System.out.println(courseDTO.toString());
        try {
            courseService.updateCourse(courseDTO);
            return "redirect:/course";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        try {
            courseService.deleteCourse(id);
            return "redirect:/course";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
