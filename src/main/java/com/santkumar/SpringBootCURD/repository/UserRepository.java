package com.santkumar.SpringBootCURD.repository;

import com.santkumar.SpringBootCURD.entity.Student;
import com.santkumar.SpringBootCURD.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);


  Optional<User> findByProviderAndProviderSubject(
          String provider,
          String providerSubject
  );

  Optional<User> findByEmail(String email);

}
