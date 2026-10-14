package com.santkumar.SpringBootCURD.dto.studentdto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequestDto {
    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Age is required")
    private Integer age;

    @NotBlank(message = "Phone number is required")
    private String phone;

    @NotBlank(message = "Course is required")
    private String course;
}
