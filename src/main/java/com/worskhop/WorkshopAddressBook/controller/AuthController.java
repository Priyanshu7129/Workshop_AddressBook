package com.worskhop.WorkshopAddressBook.controller;

import com.worskhop.WorkshopAddressBook.dto.AuthUserDTO;
import com.worskhop.WorkshopAddressBook.dto.ResponseDTO;
import com.worskhop.WorkshopAddressBook.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO<String>> register(@Valid @RequestBody AuthUserDTO authUserDTO) {
        String message = authService.register(authUserDTO);
        return ResponseEntity.ok(new ResponseDTO<>(message, null));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<String>> login(@Valid @RequestBody AuthUserDTO authUserDTO) {
        String token = authService.login(authUserDTO);
        return ResponseEntity.ok(new ResponseDTO<>("Login successful", token));
    }
}
