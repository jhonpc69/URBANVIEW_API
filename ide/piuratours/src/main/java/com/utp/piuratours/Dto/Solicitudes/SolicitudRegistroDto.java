package com.utp.piuratours.Dto.Solicitudes;

import com.utp.piuratours.Dto.Imagenes.ImagenesDto;
import com.utp.piuratours.Models.Imagenes;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SolicitudRegistroDto {
    private Integer idSolicitud;
    private Integer idCategoria;
    private Integer idLocal;
    private Integer idUsuario;
    private String nombreLocal;
    private String telefono;
    private String valoracion;
    private String descripcion;
    private String horaApertura;
    private String horaCierre;
    private String Estado;
    private String fechaRegistro;
    private String linkInstagram;
    private String linkFacebook;
    private String linkMaps;
    private String ubicacion;
    private String comentarioAdmin;
    private List<ImagenesDto> imagenes;

}
