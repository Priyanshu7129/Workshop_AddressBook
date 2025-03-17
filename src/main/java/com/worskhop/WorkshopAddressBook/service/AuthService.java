package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.dto.AuthUserDTO;
import com.worskhop.WorkshopAddressBook.model.User;
import com.worskhop.WorkshopAddressBook.repository.UserRepository;
import com.worskhop.WorkshopAddressBook.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(AuthUserDTO authUserDTO) {
        Optional<User> existingUser = userRepository.findByUsername(authUserDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Username already taken.");
        }

        User user = new User();
        user.setUsername(authUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));

        userRepository.save(user);
        return "User registered successfully.";
    }

    public String login(AuthUserDTO authUserDTO) {
        User user = userRepository.findByUsername(authUserDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(authUserDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
