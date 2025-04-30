package com.utp.piuratours.Services;

import com.utp.piuratours.Dto.Token.TokenResponse;
import com.utp.piuratours.Models.Usuarios;

import javax.validation.constraints.NotNull;

public interface AuthService {

     TokenResponse authenticate(String email,String password);
     void saveUserToken(Usuarios user, String jwtToken);
     void revokeAllUserTokens(final Usuarios user);
     TokenResponse refreshToken(@NotNull final String authentication);
}
