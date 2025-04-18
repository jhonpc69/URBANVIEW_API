package com.utp.piuratours.Dto.Locales;

import com.utp.piuratours.Models.Categoria;
import com.utp.piuratours.Models.Publicaciones;
import com.utp.piuratours.Models.Solicitudes;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class LocalesDto {
    private Integer idLocal;
    private String nombreLocal;
    private String direccion;
    private String telefono;
    private String valoracion;
    private String descripcion;
    private String horaApertura;
    private String horaCierre;
    private String estado;
    private Integer idCategoria;
}
