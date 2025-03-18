package com.worskhop.WorkshopAddressBook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendResetPasswordEmail(String to, String resetToken) {
        if (!to.contains("@")) {  // ✅ Ensure it's a valid email
            throw new IllegalArgumentException("Invalid email address: " + to);
        }

        String subject = "Password Reset Request";
        String resetLink = "http://localhost:8080/api/auth/reset-password?token=" + resetToken;
        String body = "Click the following link to reset your password: <a href=\"" + resetLink + "\">Reset Password</a>";

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body, true);
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email: " + e.getMessage());
        }
    }
}
