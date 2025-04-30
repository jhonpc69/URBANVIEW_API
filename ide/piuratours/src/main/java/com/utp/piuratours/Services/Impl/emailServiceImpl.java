package com.utp.piuratours.Services.Impl;


import com.utp.piuratours.Services.emailService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class emailServiceImpl implements emailService {

    private final JavaMailSender mailSender;
    @Override
    public void sendVerificationCode(String to, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Tu código de verificación");
        message.setText("Tu código es: " + code + "\nVálido por 5 minutos.");
        mailSender.send(message);
    }


}