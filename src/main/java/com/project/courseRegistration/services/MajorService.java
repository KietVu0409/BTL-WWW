package com.project.courseRegistration.services;

import com.project.courseRegistration.dtos.MajorDTO;
import com.project.courseRegistration.models.Faculty;
import com.project.courseRegistration.models.Major;

import java.util.List;

public interface MajorService {

    List<Major> getMajors();

    Major getMajor(Long id);

    Major insertMajor(MajorDTO majorDTO);

    void updateMajor(MajorDTO majorDTO);

    void deleteMajor(Long id);
}
