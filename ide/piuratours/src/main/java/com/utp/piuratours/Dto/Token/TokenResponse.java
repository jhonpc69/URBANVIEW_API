package com.utp.piuratours.Dto.Token;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.utp.piuratours.Dto.Usuarios.UsuarioRespuestaLogin;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TokenResponse {
    @JsonProperty("access_token")
    String accessToken;
    @JsonProperty("refresh_token")
    String refreshToken;
    @JsonProperty("usuario")
    UsuarioRespuestaLogin usuario;

}
