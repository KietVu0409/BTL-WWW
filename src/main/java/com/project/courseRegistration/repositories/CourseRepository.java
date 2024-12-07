package com.project.courseRegistration.repositories;

import com.project.courseRegistration.models.Course;
import com.project.courseRegistration.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByName(String name);
    @Query("SELECT c FROM Course c WHERE c.subject.semester = :semester " +
            "AND c.subject.major.id = :major_id AND c.subject.status = :status")
    List<Course> findSubjectForUser(@Param("semester") int semester,
                                    @Param("major_id") Long majorId,
                                    @Param("status") String status);
}
