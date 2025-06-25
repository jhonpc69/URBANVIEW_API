package com.utp.piuratours.Dto.Publicaciones;

import com.utp.piuratours.Dto.Comentarios.ComentariosDto;
import com.utp.piuratours.Models.Comentarios;
import com.utp.piuratours.Models.Locales;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PublicacionesDto {
    private Integer idUsuario;
    private Integer idPublicacion;
    private Integer idLocal;
    private String Titulo;
    private String descripcion;
    private String horaApertura;
    private String horaCierre;
    private String estado;
    private String fechaPublicacion;
    private String linkInstagram;
    private String linkFacebook;
    private String linkMaps;
    private List<ComentariosDto> comentarios;
    private List<byte[]> imagenes;
    private Double valoracion;
}
