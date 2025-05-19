package com.alibarandemir.isin_asli_backend.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendResetPasswordEmail(String toEmail, String token) {
        String resetUrl = "http://localhost:3000/reset-password?token=" + token;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Şifre Sıfırlama Bağlantısı");
        message.setText("Aşağıdaki bağlantıya tıklayarak şifrenizi sıfırlayabilirsiniz:\n\n" + resetUrl);

        mailSender.send(message);
    }
}
