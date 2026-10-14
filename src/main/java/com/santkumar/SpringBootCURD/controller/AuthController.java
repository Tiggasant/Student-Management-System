package com.santkumar.SpringBootCURD.controller;

import com.santkumar.SpringBootCURD.dto.authdto.AuthResponse;
import com.santkumar.SpringBootCURD.dto.inoutdto.LoginRequest;
import com.santkumar.SpringBootCURD.dto.inoutdto.RegisterRequest;
import com.santkumar.SpringBootCURD.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;


    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterRequest request){
        System.out.println(request);
        userService.register(request);
        return ResponseEntity.ok("User register successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        AuthResponse response = userService.login(request);

        return ResponseEntity.ok(response);
    }
}
