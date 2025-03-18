package com.worskhop.WorkshopAddressBook.controller;

import com.worskhop.WorkshopAddressBook.dto.AuthUserDTO;
import com.worskhop.WorkshopAddressBook.dto.ResponseDTO;
import com.worskhop.WorkshopAddressBook.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ✅ User Registration
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<String>> register(@Valid @RequestBody AuthUserDTO authUserDTO) {
        String message = authService.register(authUserDTO);
        return ResponseEntity.ok(new ResponseDTO<>(message, null));
    }

    // ✅ User Login
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<String>> login(@Valid @RequestBody AuthUserDTO authUserDTO) {
        String token = authService.login(authUserDTO);
        return ResponseEntity.ok(new ResponseDTO<>("Login successful", token));
    }

    // ✅ Forgot Password (Send Reset Email)
    @PostMapping("/forgot-password")
    public ResponseEntity<ResponseDTO<String>> forgotPassword(@RequestBody Map<String, String> request) {
        String message = authService.forgotPassword(request.get("username"));
        return ResponseEntity.ok(new ResponseDTO<>(message, null));
    }

    // ✅ Reset Password (Use Token)
    @PostMapping("/reset-password")
    public ResponseEntity<ResponseDTO<String>> resetPassword(@RequestParam String token, @RequestBody Map<String, String> request) {
        String message = authService.resetPassword(token, request.get("newPassword"));
        return ResponseEntity.ok(new ResponseDTO<>(message, null));
    }
}
