package com.project.courseRegistration.controllers;

import com.project.courseRegistration.dtos.StudentDTO;
import com.project.courseRegistration.models.Student;
import com.project.courseRegistration.services.MajorService;
import com.project.courseRegistration.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final MajorService majorService;

    @GetMapping("")
    public String show(Model model) {
        List<Student> students = studentService.getStudents();
        model.addAttribute("students", students);
        return "admin/students/overview";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("majors", majorService.getMajors());
        return "admin/students/add";
    }

    @GetMapping("/modify")
    public String modify(Model model, Long id) {
        model.addAttribute("majors", majorService.getMajors());
        model.addAttribute("student", studentService.getStudentById(id));
        return "admin/students/update";
    }

    @PostMapping("/insert")
    public String insert(StudentDTO studentDTO,
                                 BindingResult result,
                                 Model model) {
        try {
            Student student = studentService.insertStudent(studentDTO);
            return "redirect:/student";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/update")
    public String update(StudentDTO studentDTO,
                                Model model) {
        try {
            Student student = studentService.updateStudent(studentDTO);
            return "redirect:/student";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        try {
            studentService.deleteStudent(id);
            return "redirect:/student";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("user/show/{id}")
    public String info(Model model, @PathVariable Long id) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "web/student/info";
    }

        @GetMapping("user/modify")
    public String userModify(Model model, Long id) {
        model.addAttribute("student", studentService.getStudentById(id));
        return "web/student/update";
    }

    @PostMapping("user/update")
    public String userUpdate(StudentDTO studentDTO, Model model) {
        System.out.println(studentDTO.toString());
        try {
            studentService.userUpdate(studentDTO);
            return "redirect:show/" + studentDTO.getId();
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }


}
