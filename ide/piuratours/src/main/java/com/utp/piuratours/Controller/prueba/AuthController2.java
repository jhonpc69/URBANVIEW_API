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
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController2 {

    private final AuthService service;
    private final AuthenticationManager authenticationManager;
    private final UsuariosRepository repository;
    private final emailService emailService;
    private final VerificationCodeRepository codeRepository;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate2(@RequestParam String email, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        final Usuarios user = repository.getUsuariosByCorreo(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        VerificationCode existingCode = codeRepository.findByUsuario(user)
                .orElse(null);
        // Verificamos si existe un código expirado o usado y lo eliminamos

        if (existingCode != null && existingCode.getCode().equals("0")) {
            // Si el código está marcado como "0" (usado o expirado), lo eliminamos
            codeRepository.delete(existingCode);
        }
        // Verificamos si ya existe un código activo
        if (existingCode != null && existingCode.getExpirationTime().isAfter(LocalDateTime.now()) && !existingCode.getCode().equals("0")) {
            return ResponseEntity.badRequest().body(new LoginDto(false, "Ya existe un codigo enviado para este usuario"));
        }

        // Generamos y enviamos un nuevo código de verificación
        String verificationCode = generateVerificationCode();
        emailService.sendVerificationCode(user.getCorreo(), verificationCode);
        saveVerificationCode(user, verificationCode);

        return ResponseEntity.ok(new LoginDto(true, "Se envio el correo correctamente"));
    }



    //

//    @PostMapping("/login")
//    public ResponseEntity<TokenResponse> authenticate(@RequestParam String email,@RequestParam String password) {
//        final TokenResponse response = service.authenticate(email,password);
//        return ResponseEntity.ok(response);
//    }
//

    //






    @CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.POST})
    @PostMapping("/verify-code")
    public ResponseEntity<?> verifyCode(@RequestParam String email, @RequestParam String code, @RequestParam String claveUsuario) {
        Usuarios user = repository.getUsuariosByCorreo(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));


        // Buscamos el código de verificación
        VerificationCode verification = codeRepository.findByUsuario(user)
                .orElseThrow(() -> new RuntimeException("Código no encontrado para este usuario"));

        // Si el código ha expirado, lo inactivamos (lo seteamos en 0)
        if (verification.getExpirationTime().isBefore(LocalDateTime.now())) {
            verification.setCode("0");  // Marcamos el código como expirado
            codeRepository.save(verification);
            return ResponseEntity.badRequest().body("El código ha expirado. Por favor, solicita uno nuevo.");
        }

        // Si el código es incorrecto
        if (!verification.getCode().equals(code)) {
            return ResponseEntity.badRequest().body("Código incorrecto o ya fue utilizado");
        }

        // El código es válido, lo eliminamos para que no se reutilice
        verification.setCode("0");  // Marcamos el código como usado
        codeRepository.save(verification);

        // Generamos el token de autenticación
        final TokenResponse response = service.authenticate(email, claveUsuario);
        return ResponseEntity.ok(response);
    }






    public String generateVerificationCode() {
        int code = (int) (Math.random() * 900000) + 100000; // Genera un código de 6 dígitos
        return String.valueOf(code);
    }

    private void saveVerificationCode(Usuarios user, String code) {
        // Verificamos si ya existe un código, y si es así, lo inactivamos
        codeRepository.findByUsuario(user).ifPresent(existingCode -> {
            if (!existingCode.getCode().equals("0")) {
                existingCode.setCode("0"); // Marcamos el código como expirado si existe
                codeRepository.save(existingCode);
            }
        });

        // Guardamos el nuevo código de verificación
        VerificationCode verification = new VerificationCode();
        verification.setUsuario(user);
        verification.setCode(code);
        verification.setExpirationTime(LocalDateTime.now().plusMinutes(10)); // El código es válido por 10 minutos
        codeRepository.save(verification);
    }
}
