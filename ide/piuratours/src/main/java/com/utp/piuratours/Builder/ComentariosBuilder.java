package com.utp.piuratours.Builder;

import com.utp.piuratours.Dto.Comentarios.ComentariosDto;
import com.utp.piuratours.Dto.Usuarios.UsuarioRespuestaRegistroDto;
import com.utp.piuratours.Models.Comentarios;
import com.utp.piuratours.Models.Usuarios;

import java.util.List;
import java.util.stream.Collectors;

public class ComentariosBuilder {
    public static List<ComentariosDto> BuildearComentarios(List<Comentarios> list){
        return list.stream().map(comentario -> ComentariosDto.builder()
                .idPublicacion(comentario.getIdComentario())
                .nombreUsuario(comentario.getIdUsuario().getNombreUsuario())
                .Contenido(comentario.getContenido())
                .fechaComentario(comentario.getFechaComentario())
                .build()).collect(Collectors.toList());

    }
}

