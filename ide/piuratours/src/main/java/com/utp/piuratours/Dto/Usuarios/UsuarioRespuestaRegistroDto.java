package com.utp.piuratours.Dto.Usuarios;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UsuarioRespuestaRegistroDto {
    private String nombreUsuario;
    private String apellidosUsuario;
    private String dniUsuario;
    private String telefono;
    private String correo;
    private String tipoUsuario;
}
