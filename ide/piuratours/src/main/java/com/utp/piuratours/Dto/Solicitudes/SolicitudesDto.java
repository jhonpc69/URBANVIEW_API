package com.utp.piuratours.Dto.Solicitudes;

import com.utp.piuratours.Dto.Imagenes.ImagenesDto;
import com.utp.piuratours.Models.Locales;
import com.utp.piuratours.Models.Usuarios;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SolicitudesDto {
    private Integer idLocal;
    private Integer idUsuario;
    private String Estado;
    private String comentarioAdmin;
    private String fechaRegistro;
    private List<ImagenesDto> imagenes;
}
