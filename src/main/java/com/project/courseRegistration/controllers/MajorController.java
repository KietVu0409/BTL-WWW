package com.project.courseRegistration.controllers;

import com.project.courseRegistration.dtos.MajorDTO;
import com.project.courseRegistration.models.Faculty;
import com.project.courseRegistration.models.Major;
import com.project.courseRegistration.services.FacultyService;
import com.project.courseRegistration.services.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/major")
@RequiredArgsConstructor
public class MajorController {
    private final MajorService majorService;

    private final FacultyService facultyService;

    @GetMapping("")
    public String show(Model model) {
        List<Major> majors = majorService.getMajors();
        model.addAttribute("majors", majors);
        return "admin/majors/overview";
    }

    @GetMapping("/add")
    public String add(Model model) {
        List<Faculty> faculties = facultyService.getFaculties();
        model.addAttribute("faculties", faculties);
        return "admin/majors/add";
    }

    @GetMapping("/modify")
    public String modify(Model model,
                         Long id) {
        List<Faculty> faculties = facultyService.getFaculties();
        model.addAttribute("major", majorService.getMajor(id));
        model.addAttribute("faculties", faculties);
        return "admin/majors/update";
    }

    @PostMapping("/insert")
    public String insert(MajorDTO majorDTO,
                                Model model) {
        try {
            Major major = majorService.insertMajor(majorDTO);
            return "redirect:/major";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/update")
    public String update(MajorDTO majorDTO,
                                Model model) {
        try {
            majorService.updateMajor(majorDTO);
            return "redirect:/major";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/delete")
    public String delete(Model model, @RequestParam("id") Long id) {
        try {
            majorService.deleteMajor(id);
            return "redirect:/major";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
