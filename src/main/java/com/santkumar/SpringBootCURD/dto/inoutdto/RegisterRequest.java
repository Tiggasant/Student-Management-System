package com.santkumar.SpringBootCURD.dto.inoutdto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must not exceed 100 character")
    private String username;

    private String password;

    @NotBlank(message = "Email is Required")
    @Email(message = "Please enter a valid email")
    private String email;


}
