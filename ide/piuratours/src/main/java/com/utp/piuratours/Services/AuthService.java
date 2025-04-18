package com.utp.piuratours.Services;

import com.utp.piuratours.Dto.Token.TokenResponse;
import com.utp.piuratours.Models.Usuarios;

import javax.validation.constraints.NotNull;

public interface AuthService {

    public TokenResponse authenticate(String email,String password);
    public void saveUserToken(Usuarios user, String jwtToken);
    public void revokeAllUserTokens(final Usuarios user);
    public TokenResponse refreshToken(@NotNull final String authentication);
}
