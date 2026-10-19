package com.utp.piuratours.Controller.prueba;

import com.utp.piuratours.Dto.Token.TokenResponse;
import com.utp.piuratours.Dto.login.LoginDto;
import com.utp.piuratours.Models.Usuarios;
import com.utp.piuratours.Models.VerificationCode;
import com.utp.piuratours.Repository.UsuariosRepository;
import com.utp.piuratours.Repository.VerificationCodeRepository;
import com.utp.piuratours.Services.AuthService;
import com.utp.piuratours.Services.emailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController2 {
    private static final SecureRandom RANDOM = new SecureRandom();
    private final AuthService service;
    private final AuthenticationManager authenticationManager;
    private final UsuariosRepository repository;
    private final emailService emailService;
    private final VerificationCodeRepository codeRepository;

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<LoginDto> authenticate2(@RequestParam String email, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        Usuarios user = repository.getUsuariosByCorreo(email).orElseThrow();
        VerificationCode verification = codeRepository.findByUsuario(user).orElseGet(VerificationCode::new);
        if (verification.getExpirationTime() != null
                && verification.getExpirationTime().isAfter(LocalDateTime.now())
                && !"0".equals(verification.getCode())) {
            // Resend the same unexpired code, keeping its original expiry and row.
            emailService.sendVerificationCode(user.getCorreo(), verification.getCode());
            return ResponseEntity.ok(new LoginDto(true, "Usa el código que ya enviamos a tu correo"));
        }
        String code = generateVerificationCode();
        verification.setUsuario(user);
        verification.setCode(code);
        verification.setExpirationTime(LocalDateTime.now().plusMinutes(10));
        codeRepository.save(verification);
        emailService.sendVerificationCode(user.getCorreo(), code);
        return ResponseEntity.ok(new LoginDto(true, "Se envió el código a tu correo"));
    }

    @PostMapping("/verify-code")
    @Transactional
    public ResponseEntity<TokenResponse> verifyCode(@RequestParam String email, @RequestParam String code,
                                                    @RequestParam String claveUsuario) {
        Usuarios user = repository.getUsuariosByCorreo(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código inválido"));
        VerificationCode verification = codeRepository.findByUsuario(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Solicita un código nuevo"));
        if ("0".equals(verification.getCode()) || !verification.getCode().equals(code)
                || !verification.getExpirationTime().isAfter(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Código incorrecto, usado o expirado");
        }
        TokenResponse response = service.authenticate(email, claveUsuario);
        verification.setCode("0");
        codeRepository.save(verification);
        return ResponseEntity.ok(response);
    }

    public String generateVerificationCode() {
        return String.valueOf(100000 + RANDOM.nextInt(900000));
    }
}
