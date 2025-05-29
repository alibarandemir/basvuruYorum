package com.alibarandemir.isin_asli_backend.controller;

import com.alibarandemir.isin_asli_backend.dto.LoginRequestDto;
import com.alibarandemir.isin_asli_backend.dto.RegisterRequestDto;
import com.alibarandemir.isin_asli_backend.dto.ResponseDto;
import com.alibarandemir.isin_asli_backend.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")

public class AuthController {

    private final AuthService authenticationService;

    public AuthController(AuthService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDto<String>> register(
            @Valid @ModelAttribute RegisterRequestDto request) {
        ResponseDto<String> response = authenticationService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto<String>> login(@Valid @RequestBody LoginRequestDto request) {
        ResponseDto<String> response = authenticationService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDto<String>> forgotPassword(@RequestParam String email) {
        ResponseDto<String> response = authenticationService.forgotPassword(email);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDto<String>> resetPassword(@RequestBody String token,@RequestBody String password){
        ResponseDto<String> response = authenticationService.resetPassword(token,password);
        return ResponseEntity.ok(response);
    }
}
