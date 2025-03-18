package com.worskhop.WorkshopAddressBook.controller;

import com.worskhop.WorkshopAddressBook.dto.AuthUserDTO;
import com.worskhop.WorkshopAddressBook.dto.ResponseDTO;
import com.worskhop.WorkshopAddressBook.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication API", description = "Endpoints for user authentication")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Registers a user and publishes an event to RabbitMQ.")
    public ResponseEntity<ResponseDTO<String>> register(@Valid @RequestBody AuthUserDTO authUserDTO) {
        String message = authService.register(authUserDTO);
        return ResponseEntity.ok(new ResponseDTO<>(message, null));
    }
}
