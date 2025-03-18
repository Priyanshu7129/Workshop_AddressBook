package com.worskhop.WorkshopAddressBook.service;

import com.worskhop.WorkshopAddressBook.dto.AuthUserDTO;
import com.worskhop.WorkshopAddressBook.model.User;
import com.worskhop.WorkshopAddressBook.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String register(AuthUserDTO authUserDTO) {
        Optional<User> existingUser = userRepository.findByUsername(authUserDTO.getUsername());
        if (existingUser.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username already taken.");
        }

        User user = new User();
        user.setUsername(authUserDTO.getUsername());
        user.setPassword(passwordEncoder.encode(authUserDTO.getPassword()));

        userRepository.save(user);

        // ✅ Publish event to RabbitMQ
        rabbitMQProducer.sendUserRegistrationMessage("New user registered: " + authUserDTO.getUsername());

        return "User registered successfully.";
    }
}
