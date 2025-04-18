package com.utp.piuratours.Dto.Solicitudes;

import com.utp.piuratours.Dto.Imagenes.ImagenesDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SolicitudAceptadaDto {
    private Integer idSolicitud;
    private Integer idCategoria;
    private Integer idLocal;
    private Integer idUsuario;
    private String nombreLocal;
    private String direccion;
    private String telefono;
    private String valoracion;
    private String horaApertura;
    private String horaCierre;
    private String Estado;
    private String comentarioAdmin;
    private List<ImagenesDto> imagenes;
}

