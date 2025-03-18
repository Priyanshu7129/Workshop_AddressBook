package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.dto.AuthUserDTO;
import com.worskhop.WorkshopAddressBook.model.User;
import com.worskhop.WorkshopAddressBook.repository.UserRepository;
import com.worskhop.WorkshopAddressBook.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ✅ Register a new user
    public String register(AuthUserDTO authUserDTO) {
        Optional<User> existingUser = userRepository.findByUsername(authUserDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken.");
        }

        User user = new User();
        user.setUsername(authUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));

        userRepository.save(user);
        return "User registered successfully.";
    }

    // ✅ Login and generate JWT token
    public String login(AuthUserDTO authUserDTO) {
        User user = userRepository.findByUsername(authUserDTO.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password"));

        if (!passwordEncoder.matches(authUserDTO.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }

    // ✅ Forgot Password - Generate and Send Reset Token
    public String forgotPassword(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        user.setTokenExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000)); // 15 min expiry
        userRepository.save(user);

        emailService.sendResetPasswordEmail(user.getUsername(), resetToken);
        return "Password reset email sent.";
    }

    // ✅ Reset Password using Token
    public String resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetToken(token)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid reset token"));

        if (user.getTokenExpiration().before(new Date())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Reset token has expired");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setTokenExpiration(null);
        userRepository.save(user);

        return "Password has been successfully reset.";
    }
}
