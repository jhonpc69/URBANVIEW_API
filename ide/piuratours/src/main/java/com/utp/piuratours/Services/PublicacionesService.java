package com.utp.piuratours.Services;


import com.utp.piuratours.Dto.Publicaciones.PublicacionesDto;
import com.utp.piuratours.Models.Publicaciones;

import java.util.List;

public interface PublicacionesService {

    Publicaciones GuardarPublicacion(Publicaciones publicacion);

    List<PublicacionesDto> ObtenerTodasPublicaciones(Integer idCategora,String titulo);
    List<PublicacionesDto> ObtenerTodasPublicaciones2(Integer idCategora);

    Publicaciones obtenerPublicacionId(Integer idPublicacion);
}
