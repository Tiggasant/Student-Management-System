package com.santkumar.SpringBootCURD.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String username;
    private String password;

    @Column(unique = true)
    private String email;

    private String provider;        // LOCAL or GOOGLE

    private String providerSubject; // Google's stable "sub" ID

}
