package com.project.courseRegistration.services.implement;

import java.util.List;
import java.util.Optional;

import com.project.courseRegistration.dtos.FacultyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.courseRegistration.models.Faculty;
import com.project.courseRegistration.repositories.FacultyRepository;
import com.project.courseRegistration.services.FacultyService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService{
	private final FacultyRepository facultyRepository;
	
	@Override
	public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
	}

    @Override
    public Faculty getFacultyById(Long id) {
        return facultyRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Faculty not found"));
    }

    @Override
	public Faculty insertFaculty(FacultyDTO facultyDTO){
		Faculty faculty = facultyRepository.findByName(facultyDTO.getName());
		if(faculty == null) {
			Faculty newFaculty = Faculty.builder()
					.name(facultyDTO.getName())
					.build();
			return facultyRepository.save(newFaculty);
		}
		return null;
	}

	@Override
	public void deleteFaculty(Long id) {
		Optional<Faculty> optionalFaculty = facultyRepository.findById(id);
		optionalFaculty.ifPresent(facultyRepository::delete);
	}

	@Override
	public void updateFaculty(FacultyDTO facultyDTO) {
		Faculty faculty = facultyRepository.findById(facultyDTO.getId())
				.orElseThrow(()-> new RuntimeException("Faculty not found"));
		faculty.setName(facultyDTO.getName());
		faculty.setId(facultyDTO.getId());
		facultyRepository.save(faculty);
	}
}
