package com.project.courseRegistration.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
    private Long id;

    private String name;

    private int limitedNumber;

    private Long subject_id;

    private Long teacher_id;
}
