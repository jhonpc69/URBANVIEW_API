package com.utp.piuratours.Services;


public interface emailService {
    void sendVerificationCode(String to, String code);
}
