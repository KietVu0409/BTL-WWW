package com.project.courseRegistration.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MajorDTO {
    private Long id;

    private String name;

    private Long faculty_id;
}
