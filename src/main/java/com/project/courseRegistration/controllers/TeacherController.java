package com.project.courseRegistration.controllers;

import com.project.courseRegistration.dtos.TeacherDTO;
import com.project.courseRegistration.models.Teacher;
import com.project.courseRegistration.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("")
    public String show(Model model) {
        List<Teacher> teachers = teacherService.getAllTeachers();
        model.addAttribute("teachers", teachers);
        return "admin/teachers/overview";
    }

    @GetMapping("/modify")
    public String addOrUpdateFaculty(Long id,
                                     Model model) {
        Teacher teacher = new Teacher();
        if (id != null) {
            teacher = teacherService.getTeacherById(id);
        }
        model.addAttribute("teacher", teacher);
        return "admin/teachers/addOrUpdate";
    }

    @PostMapping("/insert")
    public String insert(TeacherDTO teacherDTO,
                         Model model) {
        try {
            Teacher teacher = teacherService.addTeacher(teacherDTO);
            return "redirect:/teacher";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/update")
    public String update(TeacherDTO teacherDTO, Model model) {
        try {
            teacherService.updateTeacher(teacherDTO);
            return "redirect:/teacher";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }

    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") Long id, Model model) {
        try {
            teacherService.deleteTeacher(id);
            return "redirect:/teacher";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
