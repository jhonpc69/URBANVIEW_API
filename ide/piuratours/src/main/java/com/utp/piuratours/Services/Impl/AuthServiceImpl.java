package com.utp.piuratours.Services.Impl;

import com.utp.piuratours.Dto.Token.TokenResponse;
import com.utp.piuratours.Dto.Usuarios.UsuarioRespuestaLogin;
import com.utp.piuratours.Models.Token;
import com.utp.piuratours.Models.Usuarios;
import com.utp.piuratours.Repository.TokenRepository;
import com.utp.piuratours.Repository.UsuariosRepository;
import com.utp.piuratours.Services.AuthService;
import com.utp.piuratours.Services.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UsuariosRepository repository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public TokenResponse authenticate(String email, String password) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        final Usuarios user = repository.getUsuariosByCorreo(email)
                .orElseThrow();
        final String accessToken = jwtService.generateToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);
        return new TokenResponse(accessToken, refreshToken,new UsuarioRespuestaLogin(user.getIdUsuario(), Boolean.TRUE, user.getNombreUsuario()+" "+user.getApellidosUsuario() ,user.getDniUsuario(), user.getIdTipoUsuario().getIdTipoUsuario()),Boolean.TRUE);
    }

    @Override
    public TokenResponse refreshToken(String authentication) {
        if (authentication == null || !authentication.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Invalid auth header");
        }
        final String refreshToken = authentication.substring(7);
        final String userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail == null) {
            return null;
        }

        final Usuarios user = this.repository.getUsuariosByDniUsuario(userEmail).orElseThrow();
        final boolean isTokenValid = jwtService.isTokenValid(refreshToken, user);
        if (!isTokenValid) {
            return null;
        }

        final String accessToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken);

        return new TokenResponse(accessToken, refreshToken,new UsuarioRespuestaLogin(user.getIdUsuario(), Boolean.TRUE, user.getNombreUsuario()+" "+user.getApellidosUsuario() ,user.getDniUsuario(), user.getIdTipoUsuario().getIdTipoUsuario()),Boolean.TRUE);

    }


    @Override
    public void saveUserToken(Usuarios user, String jwtToken) {
        final Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(Token.TokenType.BEARER)
                .isExpired(false)
                .isRevoked(false)
                .build();
        tokenRepository.save(token);
    }

    @Override
    public void revokeAllUserTokens(Usuarios user) {
        final List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(user.getIdUsuario());
        if (!validUserTokens.isEmpty()) {
            validUserTokens.forEach(token -> {
                token.setIsExpired(true);
                token.setIsRevoked(true);
            });
            tokenRepository.saveAll(validUserTokens);
    }
}
}