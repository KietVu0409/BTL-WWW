package com.project.courseRegistration.controllers;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.courseRegistration.dtos.FacultyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.project.courseRegistration.models.Faculty;
import com.project.courseRegistration.services.FacultyService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("faculty")
@RequiredArgsConstructor
public class FacultyController {
	private final FacultyService facultyService;

	@GetMapping("")
	public String show(Model model) {
		List<Faculty> faculties = facultyService.getFaculties();
		model.addAttribute("faculties", faculties);
		return "admin/faculties/overview";
	}

	@GetMapping("/modify")
	public String addOrUpdateFaculty(Long id,
									 Model model) {
		Faculty faculty = new Faculty();
		if (id != null) {
			faculty = facultyService.getFacultyById(id);
		}
		model.addAttribute("faculty", faculty);
		return "admin/faculties/addOrUpdate";
	}

	@PostMapping("/insert")
	public String insert(FacultyDTO facultyDTO,
								BindingResult result,
								Model model) {
		try {
			Faculty faculty = facultyService.insertFaculty(facultyDTO);
			return "redirect:/faculty";
		}catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}

	@PostMapping("/update")
	public String update(FacultyDTO facultyDTO, Model model) {
		try {
			facultyService.updateFaculty(facultyDTO);
			return "redirect:/faculty";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}

	}

	@PostMapping("/delete")
	public String delete(@RequestParam("id") Long id, Model model) {
		try {
			facultyService.deleteFaculty(id);
			return "redirect:/faculty";
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
			return "error";
		}
	}
}
