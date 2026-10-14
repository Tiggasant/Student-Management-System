package com.santkumar.SpringBootCURD.repository;

import com.santkumar.SpringBootCURD.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmailAndDeletedFalse(String email);

    Optional<Student> findByIdAndDeletedFalse(Long id);


    List<Student> findByDeletedFalse();

    boolean existsByEmail(String email);

}