package com.utp.piuratours.Dto.Comentarios;

import com.utp.piuratours.Models.Publicaciones;
import com.utp.piuratours.Models.Usuarios;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ComentariosDto {

    private Integer idPublicacion;
    private String nombreUsuario;
    private String Contenido;
    private String fechaComentario;
}
