package com.santkumar.SpringBootCURD.dto.studentdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentResponseDto {
    private Long id;
    private String name;
    private String email;
    private String course;
    private String subject;
    private String message;
    private int age;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
