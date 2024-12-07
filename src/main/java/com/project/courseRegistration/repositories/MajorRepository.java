package com.project.courseRegistration.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.courseRegistration.models.Major;

public interface MajorRepository extends JpaRepository<Major, Long> {
    Major findByName(String name);
}
