package com.worskhop.WorkshopAddressBook.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;  // ✅ This should be an email address

    @Column(nullable = false)
    private String password;

    private String resetToken;
    private Date tokenExpiration;
}
