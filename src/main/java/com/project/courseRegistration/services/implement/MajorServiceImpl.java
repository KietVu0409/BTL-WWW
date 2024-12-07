package com.project.courseRegistration.services.implement;

import com.project.courseRegistration.dtos.MajorDTO;
import com.project.courseRegistration.models.Faculty;
import com.project.courseRegistration.models.Major;
import com.project.courseRegistration.repositories.FacultyRepository;
import com.project.courseRegistration.repositories.MajorRepository;
import com.project.courseRegistration.services.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MajorServiceImpl implements MajorService {

    private final MajorRepository majorRepository;

    private final FacultyRepository facultyRepository;

    @Override
    public List<Major> getMajors() {
        return majorRepository.findAll();
    }

    @Override
    public Major getMajor(Long id) {
        return majorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Major Not Found"));
    }

    @Override
    public Major insertMajor(MajorDTO majorDTO) {
        Major major = majorRepository.findByName(majorDTO.getName());
        Faculty faculty = facultyRepository.findById(majorDTO.getFaculty_id())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        if (major == null)
            major = Major.builder()
                    .name(majorDTO.getName())
                    .faculty(faculty)
                    .build();
        return majorRepository.save(major);
    }

    @Override
    public void updateMajor(MajorDTO majorDTO) {
        Major major = majorRepository.findById(majorDTO.getId())
                .orElseThrow(() -> new RuntimeException("Major not found"));
        Faculty faculty = facultyRepository.findById(majorDTO.getFaculty_id())
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        major.setName(majorDTO.getName());
        major.setFaculty(faculty);
        major.setId(majorDTO.getId());
        majorRepository.save(major);
    }

    @Override
    public void deleteMajor(Long id) {
        Optional<Major> optionalMajor = majorRepository.findById(id);
        optionalMajor.ifPresent(majorRepository::delete);
    }
}
