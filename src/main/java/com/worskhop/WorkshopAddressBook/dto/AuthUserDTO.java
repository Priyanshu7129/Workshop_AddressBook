package com.worskhop.WorkshopAddressBook.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUserDTO {

    @NotBlank(message = "Username is required")
    @Email(message = "Invalid email format")  // ✅ Enforce email format
    private String username;

    @NotBlank(message = "Password is required")
    private String password;
}
