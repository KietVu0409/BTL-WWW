package com.project.courseRegistration.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubjectDTO {
    private Long id;

    private String name;

    private int accredit;

    private int semester;

    private double tuition;

    private Long major_id;

    private String type;
}
