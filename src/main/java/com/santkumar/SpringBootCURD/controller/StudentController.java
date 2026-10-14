package com.santkumar.SpringBootCURD.controller;

import com.santkumar.SpringBootCURD.dto.studentdto.CreateStudentRequestDto;
import com.santkumar.SpringBootCURD.dto.studentdto.CreateStudentResponseDto;
import com.santkumar.SpringBootCURD.dto.studentdto.UpdateStudentRequestDto;
import com.santkumar.SpringBootCURD.dto.UpdateStudentResponseDto;
import com.santkumar.SpringBootCURD.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")

public class StudentController {
    private final StudentService studentService;
    StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // Create Student
    @PostMapping
    public ResponseEntity<CreateStudentResponseDto> createStudent(
           @Valid @RequestBody CreateStudentRequestDto studentRequestDto) {

        CreateStudentResponseDto createdStudent =
                studentService.createStudent(studentRequestDto);
        return ResponseEntity.ok(createdStudent);
    }
    // Get Student by Id
    @GetMapping("/{id}")
    public ResponseEntity<CreateStudentResponseDto> getStudentById(@PathVariable Long id) {
        CreateStudentResponseDto student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

     //Get All Students
    @GetMapping
    public ResponseEntity<List<CreateStudentResponseDto>> getAllStudents() {
        List<CreateStudentResponseDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    // Update Student
    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(
            @PathVariable Long id,
        @Valid   @RequestBody UpdateStudentRequestDto updateStudentRequestDto) {
        UpdateStudentResponseDto updatedStudent =
                studentService.updateStudent(id, updateStudentRequestDto);
        return ResponseEntity.ok(updatedStudent);
    }


    // soft delete
    @DeleteMapping("/soft-delete/{id}")
    public String softDeleteStudent(@PathVariable Long id) {
        studentService.softDeleteStudent(id);
        return "delered Successfully";
    }
    // Delete Student

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Deleted successfully !");
    }

}
