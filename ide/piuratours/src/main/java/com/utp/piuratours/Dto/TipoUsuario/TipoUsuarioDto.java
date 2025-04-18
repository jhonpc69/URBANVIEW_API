package com.utp.piuratours.Dto.TipoUsuario;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class TipoUsuarioDto {
    private Integer idTipoUsuario;
    private String Descripcion;
}
