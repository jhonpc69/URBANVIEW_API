package com.utp.piuratours.Dto.Usuarios;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class UsuarioRespuestaLogin {
    private Integer idUsuario;
    private Boolean islogueado;
    private String nombresCompletos;
    private String dni;
    private Integer tipoUsuario;
}
