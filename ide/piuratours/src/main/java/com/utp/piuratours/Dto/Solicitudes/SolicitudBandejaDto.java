package com.utp.piuratours.Dto.Solicitudes;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class SolicitudBandejaDto {
    private Integer idSolicitud;
    private Integer idUsuario;
    private Integer idLocal;
    private String ruc;
    private String estado;
    private String nombreLocal;
    private String telefono;
    private String horaApertura;                    
    private String horaCierre;
    private String fechaRegistro;
    private Integer idCategoria;                    
    private List<byte[]> imagenes;
    private String descripcion;
    private String ubicacion;
    private String linkFacebook;
    private String linkInstagram;
    private String linkMaps;
    private String rutaPdf;
    private byte[] imgPortada;
    private byte[] imgDestacado;
}
