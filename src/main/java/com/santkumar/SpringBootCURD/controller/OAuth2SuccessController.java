package com.santkumar.SpringBootCURD.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class OAuth2SuccessController {
    @GetMapping("/oauth2/success")
    public ResponseEntity<String> success() {
        return ResponseEntity.ok(
                "Google login successful. "
                        + "You can now access protected Student APIs."
        );
    }
}
