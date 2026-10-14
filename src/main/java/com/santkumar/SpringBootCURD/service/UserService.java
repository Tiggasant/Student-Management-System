package com.santkumar.SpringBootCURD.service;

import com.santkumar.SpringBootCURD.AppConfig.JwtService;
import com.santkumar.SpringBootCURD.dto.authdto.AuthResponse;
import com.santkumar.SpringBootCURD.dto.inoutdto.LoginRequest;
import com.santkumar.SpringBootCURD.dto.inoutdto.RegisterRequest;
import com.santkumar.SpringBootCURD.entity.User;
import com.santkumar.SpringBootCURD.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public  UserService(UserRepository userRepository,
                        PasswordEncoder passwordEncoder,
                        AuthenticationManager authenticationManager,
                        JwtService jwtService
    ){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public void  register(RegisterRequest registerRequest){
      Optional<User> existingUser =  userRepository.findByUsername(registerRequest.getUsername());

        if (existingUser.isPresent()) {
            throw new IllegalArgumentException(
                    "Username already exists"
            );
        }
      // create new user
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());


        //encode the password  beforsaving
        user.setPassword(
                passwordEncoder.encode(
                        registerRequest.getPassword()
                )
        );
        userRepository.save(user);

    }


    public AuthResponse login(LoginRequest request) {
        // 1. Authenticate username and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        // 2. Generate JWT after successful authentication
        String token = jwtService.generateToken(
                request.getUsername()
        );

        // 3. Return the token
        return new AuthResponse(token);
    }
}
