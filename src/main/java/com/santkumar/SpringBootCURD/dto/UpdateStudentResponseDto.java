package com.santkumar.SpringBootCURD.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentResponseDto {
    private Long id;
    private String name;
    private int age;
    private String email;
    private String course;
    private String message;
    private LocalDateTime updatedAt;
}
