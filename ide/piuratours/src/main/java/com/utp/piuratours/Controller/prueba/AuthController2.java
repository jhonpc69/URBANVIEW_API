package com.utp.piuratours.Controller.prueba;


import com.utp.piuratours.Dto.Token.TokenResponse;
import com.utp.piuratours.Services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController2 {

    private final AuthService service;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestParam String email,@RequestParam String password) {
        final TokenResponse response = service.authenticate(email,password);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh-token")
    public TokenResponse refreshToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) final String authentication
    ) {
        return service.refreshToken(authentication);
    }
}
