package com.project.courseRegistration.services.implement;

import com.project.courseRegistration.dtos.SubjectDTO;
import com.project.courseRegistration.models.Major;
import com.project.courseRegistration.models.Subject;
import com.project.courseRegistration.models.Status;
import com.project.courseRegistration.repositories.MajorRepository;
import com.project.courseRegistration.repositories.SubjectRepository;
import com.project.courseRegistration.services.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;

    private final MajorRepository majorRepository;

    @Override
    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Subject findById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow((() -> new RuntimeException("Subject with id " + id + " not found")));
    }

    @Override
    public Subject insertSubject(SubjectDTO subjectDTO) {
        Subject subject = subjectRepository.findByName(subjectDTO.getName());
        Major major = majorRepository.findById(subjectDTO.getMajor_id())
                .orElseThrow(() -> new IllegalArgumentException("Major id not found"));
        if (subject == null) {
            Subject newSubject = Subject.builder()
                    .name(subjectDTO.getName())
                    .accredit(subjectDTO.getAccredit())
                    .type(subjectDTO.getType())
                    .tuition(subjectDTO.getTuition())
                    .semester(subjectDTO.getSemester())
                    .major(major)
                    .status(Status.UNACCOMPLISHED)
                    .build();
            return subjectRepository.save(newSubject);
        }
        return null;
    }

    @Override
    public Subject updateSubject(SubjectDTO subjectDTO) {
        Subject subject = subjectRepository.findById(subjectDTO.getId())
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        Major major = majorRepository.findById(subjectDTO.getMajor_id())
                .orElseThrow(() -> new RuntimeException("Major id not found"));
        subject.setName(subjectDTO.getName());
        subject.setAccredit(subjectDTO.getAccredit());
        subject.setType(subjectDTO.getType());
        subject.setTuition(subjectDTO.getTuition());
        subject.setSemester(subjectDTO.getSemester());
        subject.setStatus(Status.UNACCOMPLISHED);
        subject.setMajor(major);
        return subjectRepository.save(subject);
    }

    @Override
    public void deleteSubject(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        subject.ifPresent(subjectRepository::delete);
    }
}
