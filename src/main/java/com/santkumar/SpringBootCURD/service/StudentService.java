package com.santkumar.SpringBootCURD.service;

import com.santkumar.SpringBootCURD.dto.studentdto.CreateStudentRequestDto;
import com.santkumar.SpringBootCURD.dto.studentdto.CreateStudentResponseDto;
import com.santkumar.SpringBootCURD.dto.studentdto.UpdateStudentRequestDto;
import com.santkumar.SpringBootCURD.dto.UpdateStudentResponseDto;
import com.santkumar.SpringBootCURD.entity.Student;
import com.santkumar.SpringBootCURD.exception.DuplicateResourceException;
import com.santkumar.SpringBootCURD.exception.ResourceNotFoundException;
import com.santkumar.SpringBootCURD.repository.StudentRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

//Create
    public CreateStudentResponseDto createStudent(CreateStudentRequestDto studentRequestDto) {

       Student student =  mapToEntity(studentRequestDto);
        if(emailExists(student)) {
            throw new DuplicateResourceException("Student with email " + student.getEmail()
                    + " already exists");
        }
        Student studentResp = studentRepository.save(student);
        return mapToDto(studentResp);
        }

        // Get by Id
    public CreateStudentResponseDto getStudentById(Long id) {
       Student  student =  studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException(
                        "Student not found with id: " + id));

        return mapToDto(student);
    }

    // Get All
    public List<CreateStudentResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findByDeletedFalse();

        return students.stream()
                .map(this::mapToDto)
                .toList();
    }

    // Update
    public UpdateStudentResponseDto updateStudent(
            Long id, UpdateStudentRequestDto updateStudentRequestDto) {
        Student student =  studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id));


        // update field
        student.setName(updateStudentRequestDto.getName());
        student.setPhone(updateStudentRequestDto.getPhone());
        student.setCourse(updateStudentRequestDto.getCourse());
        student.setAge(updateStudentRequestDto.getAge());
        student.setDeleted(false);
        Student updatedStudent = studentRepository.save(student);
        return mapToUpdateDto(updatedStudent);
    }

    // soft Delete
    public void softDeleteStudent(Long id) {
        Student student = studentRepository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id));
        student.setDeleted(true);
       Student student1 =  studentRepository.save(student);
    }


    //  Delete
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with id: " + id));
        studentRepository.delete(student);
    }

    // dto to entity
    private Student mapToEntity(CreateStudentRequestDto studentRequestDto) {
        Student student = new Student();

        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setEmail(studentRequestDto.getEmail());
        student.setCourse(studentRequestDto.getCourse());
        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        student.setDeleted(false);

        return student;
    }

private CreateStudentResponseDto mapToDto(Student studentResp) {
    CreateStudentResponseDto responseDto = new CreateStudentResponseDto();

    responseDto.setId(studentResp.getId());
    responseDto.setName(studentResp.getName());
    responseDto.setAge(studentResp.getAge());
    responseDto.setEmail(studentResp.getEmail());
    responseDto.setCourse(studentResp.getCourse());
    responseDto.setMessage("Student saved successfully");
    responseDto.setCreatedAt(studentResp.getCreatedAt());
    responseDto.setUpdatedAt(studentResp.getUpdatedAt());

    return responseDto;
}
    private UpdateStudentResponseDto mapToUpdateDto(Student student) {
        UpdateStudentResponseDto responseDto = new UpdateStudentResponseDto();

        responseDto.setId(student.getId());
        responseDto.setName(student.getName());
        responseDto.setAge(student.getAge());
        responseDto.setEmail(student.getEmail());
        responseDto.setCourse(student.getCourse());
        responseDto.setMessage("Student updated successfully");
        responseDto.setUpdatedAt(student.getUpdatedAt());
        return responseDto;
    }


private boolean emailExists(Student student) {
    return studentRepository.existsByEmail(student.getEmail());
}

}
