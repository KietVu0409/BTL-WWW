package com.project.courseRegistration.repositories;

import com.project.courseRegistration.models.RegistrationDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationDetailsRepository extends JpaRepository<RegistrationDetails, Long> {
    List<RegistrationDetails> findByStudent_Id(Long student_id);
}
