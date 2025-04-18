package com.utp.piuratours.Controller;

import com.utp.piuratours.Services.UsuariosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Auth2")
public class AuthController {

    private final UsuariosService usuariosService;


    public AuthController(UsuariosService usuariosService) {
        this.usuariosService = usuariosService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String dni,
                                   @RequestParam String claveusuario
    ) {
        return ResponseEntity.ok(usuariosService.login(dni, claveusuario));

    }
}