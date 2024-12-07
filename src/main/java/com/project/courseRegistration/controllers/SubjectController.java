package com.project.courseRegistration.controllers;

import com.project.courseRegistration.dtos.SubjectDTO;
import com.project.courseRegistration.models.Subject;
import com.project.courseRegistration.services.MajorService;
import com.project.courseRegistration.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    private final MajorService majorService;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        return "admin/subjects/overview";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("majors", majorService.getMajors());
        return "admin/subjects/add";
    }

    @GetMapping("/modify")
    public String modify(Model model, Long id) {
        model.addAttribute("subjects", subjectService.findAll());
        model.addAttribute("majors", majorService.getMajors());
        model.addAttribute("subject", subjectService.findById(id));
        return "admin/subjects/update";
    }

    @PostMapping("/insert")
    public String insert(SubjectDTO subjectDTO,
                         Model model) {
        try {
            Subject subject = subjectService.insertSubject(subjectDTO);
            return "redirect:/subject";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/update")
    public String update(SubjectDTO subjectDTO,
                         Model model) {
        try {
            Subject subject = subjectService.updateSubject(subjectDTO);
            return "redirect:/subject";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam Long id, Model model) {
        try {
            subjectService.deleteSubject(id);
            return "redirect:/subject";
        }catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
