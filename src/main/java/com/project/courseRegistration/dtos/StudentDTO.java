package com.project.courseRegistration.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;

    private String name;

    private String gender;

    private String address;

    private String email;

    private String phone;

    private int semester;

    private Long major_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;
}
