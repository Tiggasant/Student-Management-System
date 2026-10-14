package com.santkumar.SpringBootCURD.dto.studentdto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentRequestDto {

    @NotBlank(message = "Name is required")
    @Size(min = 1, max = 50, message = "Student name must be within 2 to 50 character")
    private String name;

    @NotBlank(message = "Email is required ")
    @Email(message = "Please enter a valid email")
    private String email;

    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must contain exactly 10 digits"
    )
    private  String phone;

    @NotBlank(message = "Course is required ")
    private String course;

    @NotNull(message = "Age is required")
    @Min(value = 18, message = "Age must be at least 8")
    @Max(value = 60, message = "Age must not exceed 60")
    private  int age;
}
