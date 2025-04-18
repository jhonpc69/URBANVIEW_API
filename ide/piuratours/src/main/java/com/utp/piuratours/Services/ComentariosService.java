package com.utp.piuratours.Services;


import com.utp.piuratours.Dto.Comentarios.ComentariosDto;
import com.utp.piuratours.Models.Comentarios;
import com.utp.piuratours.Models.Publicaciones;

import java.util.List;

public interface ComentariosService {


    public ComentariosDto agregarComentarios(String comentario,Integer idPublicacion,Integer idUsuario);

    public List<ComentariosDto> findByIdPublicacion(Publicaciones idPublicacion);
}
