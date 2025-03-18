package com.worskhop.WorkshopAddressBook.repository;

import com.worskhop.WorkshopAddressBook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByResetToken(String resetToken); // ✅ Fix: Add this method
}
